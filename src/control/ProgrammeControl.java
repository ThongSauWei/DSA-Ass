/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.ProgrammeManageUI;

import entity.*;
import adt.ListInterface;
import da.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import utility.*;

/**
 *
 * @author User
 */
public class ProgrammeControl {

    //da
    private ProgrammeDA programmeDA = new ProgrammeDA();
    private TutorialGroupDA ttlDA = new TutorialGroupDA();

    //list
    private ListInterface<Programme> programmeList;
    private ListInterface<TutorialGroup> ttlList = new LinkedList<>();

    //boundary
    private ProgrammeManageUI programmeUI = new ProgrammeManageUI();

    public void runProgramme() {
        programmeList = programmeDA.readFromFile();
        ttlList = ttlDA.readFromFile();

        int choice;
        do {
            choice = programmeUI.programmeMenu();
            switch (choice) {
                case 0:
                    programmeUI.thankYou();
                    break;
                case 1:
                    listProgramme();
                    break;
                case 2:
                    addProgramme();
                    break;
                case 3:
                    updateProgramme();
                    break;
                case 4:
                    deleteProgramme();
                    break;
                case 5:
                    searchProgramme();
                    break;
                case 6:
                    addTutorialGroup();
                    break;
                case 7:
                    removeTutorialGroup();
                    break;
                case 8:
                    listTtl();
                    break;
                case 9:
                    generateReport();
                    break;
                default:
                    programmeUI.invalidInput();
                    break;
            }
        } while (choice != 0);
    }

    //display programme
    public void listProgramme() {
        programmeList = programmeDA.readFromFile();

        if (programmeList.isEmpty()) {
            programmeUI.notFound();
        } else {
            String formattedOutput = programmeUI.formatProgrammeList(programmeList);
            programmeUI.listAllProgrammes(formattedOutput);

            // Ask user if they want to sort the output
            if (programmeUI.sortComfirm()) {
                int sortOption = programmeUI.sortOptions();
                if (sortOption == 0) {
                    return;
                }
                int sortChoice = programmeUI.sortMenu();

                if (sortChoice == 1) {
                    sortProgrammeListAscending(sortOption);
                } else if (sortChoice == 2) {
                    sortProgrammeListDescending(sortOption);
                } else if (sortChoice != 3) {
                    programmeUI.invalidInput();
                }

                formattedOutput = programmeUI.formatProgrammeList(programmeList);
                programmeUI.listAllProgrammes(formattedOutput);
            }

        }
    }

    //sort
    private void sortProgrammeListAscending(int sortOption) {
        Comparator<Programme> comparator = getComparator(sortOption);
        programmeList.sort(comparator);
    }

    private void sortProgrammeListDescending(int sortOption) {
        Comparator<Programme> comparator = getComparator(sortOption).reversed();
        programmeList.sort(comparator);
    }

    private Comparator<Programme> getComparator(int sortOption) {
        Comparator<Programme> comparator = null;
        switch (sortOption) {
            case 1:
                comparator = Comparator.comparing(Programme::getProgrammeCode);
                break;
            case 2:
                comparator = Comparator.comparing(Programme::getFaculty);
                break;
            case 3:
                comparator = Comparator.comparing(Programme::getProgrammeName);
                break;
            case 4:
                comparator = Comparator.comparing(Programme::getProgrammeLevel);
                break;
            default:
                programmeUI.invalidInput();
                break;
        }
        return comparator;
    }

    //add
    public void addProgramme() {
        boolean yesNo;
        do {
            Programme newProgrammeCode;
            do {
                newProgrammeCode = programmeUI.checkProgrammeCode();

                if (programmeExists(newProgrammeCode.getProgrammeCode())) {
                    System.out.println(programmeUI.checkExists(newProgrammeCode));
                }
            } while (programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme newProgramme = programmeUI.addProgrammeInput(newProgrammeCode);

            if (newProgramme != null) {
                if (programmeUI.comfirmInput()) {
                    programmeList.add(newProgramme);
                    programmeDA.writeToFile(programmeList);
                    programmeUI.success();
                } else {
                    programmeUI.unsuccess();
                }
                // Display 
                String formattedOutput = programmeUI.formatProgrammeList(programmeList);
                programmeUI.listAllProgrammes(formattedOutput);
            }
            yesNo = programmeUI.continueInput();
        } while (yesNo == true);
    }

    //update
    public void updateProgramme() {
        boolean yesNo;
        do {
            Programme existingProgramme;

            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode())) {
                programmeUI.notExists();
                return;
            }

            existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());

            // list selected program for formatting
            LinkedList<Programme> selectedProgramList = new LinkedList<>();
            selectedProgramList.add(existingProgramme);

            String formattedOutput = programmeUI.formatProgrammeList(selectedProgramList);
            programmeUI.listAllProgrammes(formattedOutput); //display current - code

            int updateOption = programmeUI.updateProgrammeMenu();
            if (updateOption == 0) {
                return;
            } else {
                updateProgrammeData(existingProgramme, updateOption);
            }

            yesNo = programmeUI.continueInput();
        } while (yesNo == true);

    }

    private void updateProgrammeData(Programme programme, int updateOption) {
        Programme updatedProgramme = programmeUI.updateProgrammeInput(programme, updateOption);

        if (updatedProgramme != null) {
            //list
            LinkedList<Programme> updateList = new LinkedList<>();
            updateList.add(updatedProgramme);

            String formattedOutput = programmeUI.formatProgrammeList(updateList);
            programmeUI.listAllProgrammes(formattedOutput); //display current - code

            if (programmeUI.comfirmInput()) {
                int index = getIndexByProgrammeCode(updatedProgramme.getProgrammeCode());
                if (index != -1) {
                    Programme existingProgramme = programmeList.get(index);
                    updateProgrammeProperties(existingProgramme, updatedProgramme);
                    programmeDA.writeToFile(programmeList);
                    programmeUI.success();
                } else {
                    programmeUI.notFound();
                }
            } else {
                programmeUI.unsuccess();
            }

            formattedOutput = programmeUI.formatProgrammeList(programmeList);
            programmeUI.listAllProgrammes(formattedOutput);
        }
    }

    private void updateProgrammeProperties(Programme existingProgramme, Programme updatedProgramme) {
        existingProgramme.setProgrammeName(updatedProgramme.getProgrammeName());
        existingProgramme.setProgrammeDetail(updatedProgramme.getProgrammeDetail());
        existingProgramme.setProgrammeLevel(updatedProgramme.getProgrammeLevel());
        existingProgramme.setFaculty(updatedProgramme.getFaculty());
        existingProgramme.setDuration(updatedProgramme.getDuration());
    }

    private int getIndexByProgrammeCode(String programmeCode) {
        for (int i = 1; i <= programmeList.getSize(); i++) {
            Programme existingProgramme = programmeList.get(i);
            if (existingProgramme.getProgrammeCode().equals(programmeCode)) {
                return i;
            }
        }
        return -1; // not found
    }

    //delete
    public void deleteProgramme() {
        boolean continueDeletion;
        do {
            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
                programmeUI.notExists();
            } else {
                Programme existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());

                LinkedList<TutorialGroup> tutorialGroupsToDelete = new LinkedList<>();
                for (TutorialGroup tutorialGroup : ttlList) {
                    if (tutorialGroup.getProgrammeCode().equals(existingProgramme)) {
                        tutorialGroupsToDelete.add(tutorialGroup);
                    }
                }

                if (!tutorialGroupsToDelete.isEmpty()) {
                    displayTutorialGroups(existingProgramme);

                    if (programmeUI.comfirmInput()) {
                        // Get tutorial group IDs
                        ListInterface<String> tutorialGroupIdsToDelete = new LinkedList<>();
                        for (TutorialGroup tutorialGroup : tutorialGroupsToDelete) {
                            tutorialGroupIdsToDelete.add(tutorialGroup.getTutorialGroupId());
                        }

                        // Remove tutorial groups
                        for (String tutorialGroupId : tutorialGroupIdsToDelete) {
                            TutorialGroup tutorialGroupToRemove = getTutorialGroupById(tutorialGroupId, existingProgramme);
                            int indexToRemove = getIndexByTutorialGroup(tutorialGroupToRemove);
                            if (indexToRemove != -1) {
                                ttlList.remove(indexToRemove);
                            }
                        }

                        ttlDA.writeToFile(ttlList);
                    }
                }

                // Remove programme
                int indexToRemove = getIndexByProgrammeCode(existingProgramme.getProgrammeCode());
                if (indexToRemove != -1) {
                    programmeList.remove(indexToRemove);
                    programmeDA.writeToFile(programmeList);
                    programmeUI.success();
                } else {
                    programmeUI.notFound();
                }
            }

            continueDeletion = programmeUI.continueInput();
        } while (continueDeletion);
    }

    //find
    public void searchProgramme() {
        ListInterface<Programme> programmeList = programmeDA.readFromFile();
        int searchOption = programmeUI.sortOptions();

        switch (searchOption) {
            case 1:
                searchByCriteria(programmeList, "Programme Code");
                break;
            case 2:
                searchByCriteria(programmeList, "Faculty");
                break;
            case 3:
                searchByCriteria(programmeList, "Programme Name");
                break;
            case 4:
                searchByCriteria(programmeList, "Programme Level");
                break;
            case 0:
                System.out.println("Returning to the main menu.");
                break;
            default:
                programmeUI.invalidInput();
        }
    }

    private void searchByCriteria(ListInterface<Programme> programmeList, String fieldLabel) {
        String targetValue = programmeUI.getSearchInput(fieldLabel);

        Predicate<Programme> criteria = programme -> {
            String fieldValue = "";

            switch (fieldLabel) {
                case "Programme Code":
                    fieldValue = programme.getProgrammeCode();
                    break;
                case "Faculty":
                    fieldValue = programme.getFaculty();
                    break;
                case "Programme Name":
                    fieldValue = programme.getProgrammeName();
                    break;
                case "Programme Level":
                    fieldValue = String.valueOf(programme.getProgrammeLevel());
                    break;
            }

            return fieldValue.equalsIgnoreCase(targetValue);
        };

        ListInterface<Programme> results = programmeList.filter(criteria);

        if (!results.isEmpty()) {
            String formattedOutput = programmeUI.formatProgrammeList(results);
            programmeUI.listAllProgrammes(formattedOutput);
        } else {
            programmeUI.notFound();
        }
    }

    //tutorial group
    //add tutorial group to programme
    public void addTutorialGroup() {
        boolean yesNo;
        do {
            Programme newProgrammeCode;
            do {
                newProgrammeCode = programmeUI.checkProgrammeCode(); // Get programme code

                if (!programmeExists(newProgrammeCode.getProgrammeCode())) {
                    programmeUI.notExists();
                }
            } while (!programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme existingProgramme = getProgrammeByCode(newProgrammeCode.getProgrammeCode().toUpperCase());
            TutorialGroup newTutorial = programmeUI.addTutorialInput(newProgrammeCode);

            if (newTutorial != null) {
                if (programmeUI.ttlComfirm(newProgrammeCode)) {
                    ttlList.add(newTutorial);
                    ttlDA.writeToFile(ttlList);
                    programmeUI.success();
                    displayTutorialGroups(existingProgramme);
                } else {
                    programmeUI.unsuccess();
                    programmeUI.displayTtl(ttlList);
                }
            }
            yesNo = programmeUI.continueInput();
        } while (yesNo);
    }

    //delete
    public void removeTutorialGroup() {
        boolean continueRemoval;
        do {
            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
                programmeUI.notExists();
            } else {
                Programme existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());
                displayTutorialGroups(existingProgramme);

                String tutorialGroupId = programmeUI.getTutorialGroupIdToDelete();

                TutorialGroup tutorialGroupToRemove = getTutorialGroupById(tutorialGroupId, existingProgramme);

                if (tutorialGroupToRemove != null) {
                    if (programmeUI.ttlComfirm(programmeCode)) {
                        int indexToRemove = getIndexByTutorialGroup(tutorialGroupToRemove);
                        if (indexToRemove != -1) {
                            ttlList.remove(indexToRemove);
                            ttlDA.writeToFile(ttlList);
                            programmeUI.success();
                        } else {
                            programmeUI.ttlNotFound();
                        }
                    } else {
                        programmeUI.unsuccess();
                    }
                    programmeUI.displayTtl(ttlList);
                } else {
                    programmeUI.ttlNotFound();
                }
            }

            continueRemoval = programmeUI.continueInput();
        } while (continueRemoval);
    }

    //display ttl - code
    public void listTtl() {
        if (ttlList.isEmpty()) {
            programmeUI.ttlNotFound();
        } else {
            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
                programmeUI.notExists();
            } else {
                Programme existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());
                displayTutorialGroups(existingProgramme);
            }

        }
    }

    //report 
    public void generateReport() {
        int reportOption = programmeUI.report();
        switch (reportOption) {
            case 0:
                return;
            case 1:
                generateFacultyReport(programmeList);
                break;
            case 2:
                generateLevelReport(programmeList);
                break;
            default:
                programmeUI.invalidInput();
        }
    }

    public void generateFacultyReport(ListInterface<Programme> programmeList) {
        ListInterface<String> faculties = new LinkedList<>();
        ListInterface<Integer> facultyCounts = new LinkedList<>();
        int totalProgrammes = programmeList.getSize();

        for (int i = 1; i <= totalProgrammes; i++) {
            Programme programme = programmeList.get(i);
            String faculty = programme.getFaculty();

            boolean facultyExists = false;
            int facultyIndex = -1;

            for (int j = 1; j <= faculties.getSize(); j++) {
                if (faculties.get(j).equals(faculty)) {
                    facultyExists = true;
                    facultyIndex = j;
                    break;
                }
            }

            if (facultyExists) {
                int currentCount = facultyCounts.get(facultyIndex);
                facultyCounts.replace(currentCount + 1, facultyIndex);
            } else {
                faculties.add(faculty);
                facultyCounts.add(1);
            }
        }

        String highestFaculty = null;
        double highestPercentage = 0.0;

        programmeUI.reportHeader(faculties);

        int facultyNumber = 1;
        for (int i = 1; i <= faculties.getSize(); i++) {
            String faculty = faculties.get(i);
            int count = facultyCounts.get(i);
            double percentage = (count * 100.0) / totalProgrammes;

            if (percentage > highestPercentage) {
                highestPercentage = percentage;
                highestFaculty = faculty;
            }

            programmeUI.reportPercentageFaculty(facultyNumber, faculty, percentage);
            facultyNumber++;
        }

        if (highestFaculty != null) {
            programmeUI.reportHighest(highestFaculty, highestPercentage);
        } else {
            programmeUI.notFound();
        }

        // Display programme lists for each faculty
        facultyNumber = 1;
        for (int i = 1; i <= faculties.getSize(); i++) {
            String faculty = faculties.get(i);
            ListInterface<Programme> programmesInFaculty = filterProgrammesByFaculty(programmeList, faculty);
            System.out.println(facultyNumber + ". " + faculty);
            String formattedOutput = programmeUI.formatProgrammeList(programmesInFaculty);
            programmeUI.listAllProgrammes(formattedOutput);
            facultyNumber++;
        }
    }

    private int countOccurrences(ListInterface<String> list, String item) {
        int count = 0;
        for (int i = 1; i <= list.getSize(); i++) {
            if (list.get(i).equals(item)) {
                count++;
            }
        }
        return count;
    }

    public void generateLevelReport(ListInterface<Programme> programmeList) {
        ListInterface<Character> levels = new LinkedList<>();
        ListInterface<Integer> levelCounts = new LinkedList<>();
        int totalProgrammes = programmeList.getSize();

        for (int i = 1; i <= totalProgrammes; i++) {
            Programme programme = programmeList.get(i);
            Character level = programme.getProgrammeLevel();

            boolean levelExists = false;
            int levelIndex = -1;

            for (int j = 1; j <= levels.getSize(); j++) {
                if (levels.get(j).equals(level)) {
                    levelExists = true;
                    levelIndex = j;
                    break;
                }
            }

            if (levelExists) {
                int currentCount = levelCounts.get(levelIndex);
                levelCounts.replace(currentCount + 1, levelIndex);
            } else {
                levels.add(level);
                levelCounts.add(1);
            }
        }

        Character highestLevel = null;
        double highestPercentage = 0.0;

        programmeUI.reportHeaderLevel(levels);

        int levelNumber = 1;
        for (int i = 1; i <= levels.getSize(); i++) {
            Character level = levels.get(i);
            int count = levelCounts.get(i);
            double percentage = (count * 100.0) / totalProgrammes;

            if (percentage > highestPercentage) {
                highestPercentage = percentage;
                highestLevel = level;
            }

            programmeUI.reportPercentageLevel(levelNumber, level, percentage);
            levelNumber++;
        }

        if (highestLevel != null) {
            programmeUI.reportHighestLevel(highestLevel, highestPercentage);
        } else {
            programmeUI.notFound();
        }

        // Display programme lists for each level
        levelNumber = 1;
        for (int i = 1; i <= levels.getSize(); i++) {
            Character level = levels.get(i);
            Predicate<Programme> levelFilter = programme -> programme.getProgrammeLevel() == level;
            ListInterface<Programme> programmesInLevel = programmeList.filter(levelFilter);
            System.out.println(levelNumber + ". " + level);
            String formattedOutput = programmeUI.formatProgrammeList(programmesInLevel);
            programmeUI.listAllProgrammes(formattedOutput);
            levelNumber++;
        }
    }

    private void displayTutorialGroups(Programme programme) {
        LinkedList<TutorialGroup> tutorialGroupsInProgramme = new LinkedList<>();
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getProgrammeCode().equals(programme)) {
                tutorialGroupsInProgramme.add(tutorialGroup);
            }
        }

        if (tutorialGroupsInProgramme.isEmpty()) {
            programmeUI.ttlNotFound();
        } else {
            programmeUI.displayTtl(tutorialGroupsInProgramme);
        }
    }

    private int getIndexByTutorialGroup(TutorialGroup tutorialGroup) {
        for (int i = 1; i <= ttlList.getSize(); i++) {
            TutorialGroup existingTutorialGroup = ttlList.get(i);
            if (existingTutorialGroup.equals(tutorialGroup)) {
                return i;
            }
        }
        return -1;
    }

    private TutorialGroup getTutorialGroupById(String tutorialGroupId, Programme programme) {
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getTutorialGroupId().equals(tutorialGroupId) && tutorialGroup.getProgrammeCode().equals(programme)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    //get the specific programme
    private Programme getProgrammeByCode(String programmeCode) {
        for (Programme programme : programmeList) {
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }

    private ListInterface<Programme> filterProgrammesByFaculty(ListInterface<Programme> programmeList, String faculty) {
        Predicate<Programme> facultyFilter = programme -> programme.getFaculty().equalsIgnoreCase(faculty);
        return programmeList.filter(facultyFilter);
    }

    //check code exists
    private boolean programmeExists(String programmeCode) {
        String upCode = programmeCode.toUpperCase();

        Predicate<Programme> criteria = existingProgramme -> existingProgramme.getProgrammeCode().equals(upCode);

        ListInterface<Programme> filteredProgrammes = programmeList.filter(criteria);

        return !filteredProgrammes.isEmpty();
    }

    public static void main(String[] args) {
        ProgrammeControl progControl = new ProgrammeControl();
        progControl.runProgramme();
    }

//    public ListInterface<Programme> readFromFile() {
//        return programmeDA.readFromFile();
//    }
//
//    public void writeToFile(ListInterface<Programme> programmeList) {
//        programmeDA.writeToFile(programmeList);
//    }
}
