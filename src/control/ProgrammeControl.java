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
import java.util.function.Predicate;

/**
 *
 * @author Thong Sau Wei
 */
public class ProgrammeControl {

    //da
    private ProgrammeDA programmeDA = new ProgrammeDA();
    private TutorialGroupDA ttlDA = new TutorialGroupDA();
    private StudentDA studentDA = new StudentDA();
    private CourseProgrammeDA courseProgDA = new CourseProgrammeDA();

    //list
    private ListInterface<Programme> programmeList;
    private ListInterface<TutorialGroup> ttlList = new LinkedList<>();
    private ListInterface<Student> studentList = new LinkedList<>();
    private ListInterface<CourseProgramme> courseProgList = new LinkedList<>();

    //boundary
    private ProgrammeManageUI programmeUI = new ProgrammeManageUI();

    public void runProgramme() {
        programmeList = programmeDA.readFromFile();
        ttlList = ttlDA.readFromFile();
        studentList = studentDA.readFromFile();
        courseProgList = courseProgDA.readFromFile();

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
    public void listProgramme(ListInterface<Programme> programmeList) {
        String formattedOutput = programmeUI.formatProgrammeList(programmeList);
        programmeUI.listAllProgrammes(formattedOutput);
    }

    public void listProgramme() {
        programmeList = programmeDA.readFromFile();

        if (programmeList.isEmpty()) {
            programmeUI.notFound();
        } else {
            listProgramme(programmeList);

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
            }

            listProgramme(programmeList);
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
                newProgrammeCode = checkProgrammeCode();

                if (programmeExists(newProgrammeCode.getProgrammeCode())) {
                    System.out.println(programmeUI.checkExists(newProgrammeCode));
                }
            } while (programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme newProgramme = addProgrammeInput(newProgrammeCode);

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

    public Programme addProgrammeInput(Programme programmeCode) {
        String programmeName = getValidProgrammeName();
        String programmeDetail = getValidProgrammeDetail();
        char programmeLevel = getValidProgrammeLevel();
        String faculty = getValidFaculty();
        int duration = getValidDuration();

        return new Programme(
                programmeCode.getProgrammeCode().toUpperCase(),
                programmeName,
                programmeDetail,
                Character.toUpperCase(programmeLevel),
                faculty.toUpperCase(),
                duration
        );
    }

    //update
    public void updateProgramme() {
        boolean yesNo;
        do {
            Programme existingProgramme;

            Programme programmeCode = checkProgrammeCode();

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
        Programme updatedProgramme = updateProgrammeInput(programme, updateOption);

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

    public Programme updateProgrammeInput(Programme existingProgramme, int updateOption) {
        Programme updatedProgramme = new Programme(); // Create a new instance for updating

        // Copy existing data to the new instance
        updatedProgramme.setProgrammeCode(existingProgramme.getProgrammeCode());
        updatedProgramme.setProgrammeName(existingProgramme.getProgrammeName());
        updatedProgramme.setProgrammeDetail(existingProgramme.getProgrammeDetail());
        updatedProgramme.setProgrammeLevel(existingProgramme.getProgrammeLevel());
        updatedProgramme.setFaculty(existingProgramme.getFaculty());
        updatedProgramme.setDuration(existingProgramme.getDuration());

        switch (updateOption) {
            case 1:
                updatedProgramme.setProgrammeName(getValidProgrammeName());
                break;
            case 2:
                updatedProgramme.setProgrammeDetail(getValidProgrammeDetail());
                break;
            case 3:
                updatedProgramme.setProgrammeLevel(getValidProgrammeLevel());
                break;
            case 4:
                updatedProgramme.setFaculty(getValidFaculty());
                break;
            case 5:
                updatedProgramme.setDuration(getValidDuration());
                break;
            case 6:
                updatedProgramme.setProgrammeName(getValidProgrammeName());
                updatedProgramme.setProgrammeDetail(getValidProgrammeDetail());
                updatedProgramme.setProgrammeLevel(getValidProgrammeLevel());
                updatedProgramme.setFaculty(getValidFaculty());
                updatedProgramme.setDuration(getValidDuration());
                break;
            default:
                programmeUI.invalidInput();
                return null;
        }
        return updatedProgramme;
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
            Programme programmeCode = checkProgrammeCode();

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

                //course prog del
                LinkedList<CourseProgramme> courseProgToDelete = new LinkedList<>();
                for (CourseProgramme courseProg : courseProgList) {
                    if (courseProg.getProgrammeCode().equals(existingProgramme)) {
                        courseProgToDelete.add(courseProg);
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
                                LinkedList<Student> studentToDelete = new LinkedList<>();
                                for (Student student : studentList) {
                                    if (student.getTutorialGroupId().equals(tutorialGroupToRemove)) {
                                        studentToDelete.add(student);
                                    }
                                }

                                if (!studentToDelete.isEmpty()) {
                                    // displayTutorialGroups(existingProgramme);

                                    // Get tutorial group IDs
                                    ListInterface<String> studentIdsToDelete = new LinkedList<>();
                                    for (Student students : studentToDelete) {
                                        studentIdsToDelete.add(students.getStudentId());
                                    }

                                    // Remove tutorial groups
                                    for (String studentId : studentIdsToDelete) {
                                        Student studentToRemove = getStudentById(studentId, tutorialGroupToRemove);
                                        int indexToRemoveS = getIndexByStudent(studentToRemove);
                                        if (indexToRemoveS != -1) {
                                            studentList.remove(indexToRemoveS);
                                        }
                                    }

                                    studentDA.writeToFile(studentList);
                                }

                                ttlList.remove(indexToRemove);
                            }
                        }

                        ttlDA.writeToFile(ttlList);

                        //course programme
                        // Get course programme IDs
                        if (!courseProgToDelete.isEmpty()) {
                            ListInterface<String> courseProgIdsToDelete = new LinkedList<>();
                            for (CourseProgramme courseProgramme : courseProgToDelete) {
                                courseProgIdsToDelete.add(courseProgramme.getId());
                            }

                            // Remove course programme
                            for (String courseProgId : courseProgIdsToDelete) {
                                CourseProgramme courseProgToRemove = getCourseProgById(courseProgId, existingProgramme);
                                int indexToRemoveC = getIndexByCourseProgramme(courseProgToRemove);
                                if (indexToRemoveC != -1) {
                                    courseProgList.remove(indexToRemoveC);
                                }
                            }

                            courseProgDA.writeToFile(courseProgList);
                        }
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
                newProgrammeCode = checkProgrammeCode(); // Get programme code

                if (!programmeExists(newProgrammeCode.getProgrammeCode())) {
                    programmeUI.notExists();
                }
            } while (!programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme existingProgramme = getProgrammeByCode(newProgrammeCode.getProgrammeCode().toUpperCase());
            TutorialGroup newTutorial;

            boolean isUniqueTutorialID = false;

            do {
                newTutorial = programmeUI.addTutorialInput(existingProgramme);

                if (!tutorialGroupExists(newTutorial.getTutorialGroupId())) {
                    if (programmeUI.ttlComfirm(newProgrammeCode)) {
                        ttlList.add(newTutorial);
                        ttlDA.writeToFile(ttlList);
                        programmeUI.success();
                        displayTutorialGroups(existingProgramme);
                        isUniqueTutorialID = true;
                    } else {
                        programmeUI.unsuccess();
                        programmeUI.displayTtl(ttlList);
                    }
                }else{
                    programmeUI.sameTtl(); //already exits
                }

            } while (!isUniqueTutorialID); 

            yesNo = programmeUI.continueInput();
        } while (yesNo);
    }

    //delete
    public void removeTutorialGroup() {
        boolean continueRemoval;
        do {
            Programme programmeCode = checkProgrammeCode();

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

                            LinkedList<Student> studentToDelete = new LinkedList<>();
                            for (Student student : studentList) {
                                if (student.getTutorialGroupId().equals(tutorialGroupToRemove)) {
                                    studentToDelete.add(student);
                                }
                            }

                            if (!studentToDelete.isEmpty()) {
                                // displayTutorialGroups(existingProgramme);

                                // Get tutorial group IDs
                                ListInterface<String> studentIdsToDelete = new LinkedList<>();
                                for (Student students : studentToDelete) {
                                    studentIdsToDelete.add(students.getStudentId());
                                }

                                // Remove tutorial groups
                                for (String studentId : studentIdsToDelete) {
                                    Student studentToRemove = getStudentById(studentId, tutorialGroupToRemove);
                                    int indexToRemoveS = getIndexByStudent(studentToRemove);
                                    if (indexToRemoveS != -1) {
                                        studentList.remove(indexToRemoveS);
                                    }
                                }

                                studentDA.writeToFile(studentList);
                            }

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
            Programme programmeCode = checkProgrammeCode();

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

    //faculty report
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

    //level report
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
            Predicate<Programme> levelFilter = programme -> programme.getProgrammeLevel().equals(level);
            ListInterface<Programme> programmesInLevel = programmeList.filter(levelFilter);
            System.out.println(levelNumber + ". " + level);
            String formattedOutput = programmeUI.formatProgrammeList(programmesInLevel);
            programmeUI.listAllProgrammes(formattedOutput);
            levelNumber++;
        }
    }

    //validation input
    private String getValidProgrammeName() {
        String programmeName;
        do {
            programmeName = programmeUI.getProgrammeName();
            if (programmeName.trim().isEmpty()) {
                programmeUI.invalidInput();
            }
        } while (programmeName.trim().isEmpty());
        return programmeName;
    }

    private String getValidProgrammeDetail() {
        String programmeDetail;
        do {
            programmeDetail = programmeUI.getProgrammeDetail();
            if (programmeDetail.trim().isEmpty()) {
                programmeUI.invalidInput();
            }
        } while (programmeDetail.trim().isEmpty());
        return programmeDetail;
    }

    private char getValidProgrammeLevel() {
        char programmeLevel;
        do {
            String input = programmeUI.getProgrammeLevel();
            if (input.length() == 1 && Character.isLetter(input.charAt(0)) && !Character.isWhitespace(input.charAt(0))) {
                programmeLevel = Character.toUpperCase(input.charAt(0));
            } else {
                programmeUI.invalidInput();
                programmeLevel = '\0'; // Set a sentinel value for invalid input
            }
        } while (programmeLevel == '\0');
        return programmeLevel;
    }

    private String getValidFaculty() {
        String faculty;
        do {
            faculty = programmeUI.getFaculty();
            if (!faculty.matches("[A-Za-z]{4}")) {
                programmeUI.invalidInput();
            }
        } while (!faculty.matches("[A-Za-z]{4}"));
        return faculty;
    }

    private int getValidDuration() {
        int duration;
        do {
            duration = programmeUI.getDuration();
            if (duration < 1) {
                programmeUI.invalidDuration();
            }
        } while (duration < 1);
        return duration;
    }

    //get ttlgroup - programme
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

    //index
    //get ttl index
    private int getIndexByTutorialGroup(TutorialGroup tutorialGroup) {
        for (int i = 1; i <= ttlList.getSize(); i++) {
            TutorialGroup existingTutorialGroup = ttlList.get(i);
            if (existingTutorialGroup.equals(tutorialGroup)) {
                return i;
            }
        }
        return -1;
    }

    // Add a method to check if a TutorialGroup with the given ID exists
    private boolean tutorialGroupExists(String tutorialGroupId) {
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getTutorialGroupId().equalsIgnoreCase(tutorialGroupId)) {
                return true; // TutorialGroup with the same ID already exists
            }
        }
        return false; // TutorialGroup with the given ID doesn't exist
    }

    //get courseProgramme index
    private int getIndexByCourseProgramme(CourseProgramme courseProgramme) {
        for (int i = 1; i <= courseProgList.getSize(); i++) {
            CourseProgramme existingCourseProgramme = courseProgList.get(i);
            if (existingCourseProgramme.equals(courseProgramme)) {
                return i;
            }
        }
        return -1;
    }

    //get student index
    private int getIndexByStudent(Student student) {
        for (int i = 1; i <= studentList.getSize(); i++) {
            Student existingstudent = studentList.get(i);
            if (existingstudent.equals(student)) {
                return i;
            }
        }
        return -1;
    }

    //ID
    //get ttl id
    private TutorialGroup getTutorialGroupById(String tutorialGroupId, Programme programme) {
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getTutorialGroupId().equals(tutorialGroupId) && tutorialGroup.getProgrammeCode().equals(programme)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    //course programme id
    private CourseProgramme getCourseProgById(String courseProgId, Programme programme) {
        for (CourseProgramme courseProgramme : courseProgList) {
            if (courseProgramme.getId().equals(courseProgId) && courseProgramme.getProgrammeCode().equals(programme)) {
                return courseProgramme;
            }
        }
        return null;
    }

    //get student id
    private Student getStudentById(String studentId, TutorialGroup tutorialGroup) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId) && student.getTutorialGroupId().equals(tutorialGroup)) {
                return student;
            }
        }
        return null;
    }

    //get
    //get the specific programme
    private Programme getProgrammeByCode(String programmeCode) {
        for (Programme programme : programmeList) {
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }

    //get faculty - faculty report used one~
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

    public Programme checkProgrammeCode() {
        String programmeCode;
        do {
            programmeCode = programmeUI.getProgrammeCode();
            if (!isValidProgrammeCode(programmeCode)) {
                programmeUI.invalidInput();
            }
        } while (!isValidProgrammeCode(programmeCode));
        return new Programme(programmeCode);
    }

    private boolean isValidProgrammeCode(String programmeCode) {
        return programmeCode.matches("[A-Za-z]{3}");
    }
}
