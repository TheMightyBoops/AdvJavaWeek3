package us.mattgreen;

import java.util.Scanner;

public class Main {

    private final static FileInput indata = new FileInput("meals_data.csv");
    private static Scanner keyboard = new Scanner(System.in);
    private static MealsArray mealsArray = new MealsArray();

    public static void main(String[] args) {
        String line;
        String[] fields;

        while ((line = indata.fileReadLine()) != null) {
            fields = line.split(",");
            mealsArray.addElementWithStrings(fields[0],fields[1],fields[2]);
        }
        runMenu();
    }

    private static void printMenu() {
        System.out.println("Select Action");
        System.out.println("1. List All Items");
        System.out.println("2. List All Items by Meal");
        System.out.println("3. Search by Meal Name");
        System.out.println("4. Exit");
        System.out.print("Please Enter your Choice: ");
    }

    private static void runMenu() {
        char ans;
        boolean userContinue = true;
        while(userContinue) {
            printMenu();
            ans = keyboard.next().charAt(0);
            switch(ans) {
                case '1':   mealsArray.printAllMeals();break;
                case '2':   listByMeal();break;
                case '3':   searchByName();break;
                case '4':   userContinue=false;break;
            }
        }
    }
    private static void listByMeal() {
        char ans;
        int ansNum=0;
        boolean userContinue = true;
        MealType mealType;
        MealType[] mealTypeName = new MealType[10];

        System.out.println("Which Meal Type");
        int i=0;

        for(MealType m : MealType.values())
        {
            if (i < MealType.values().length) {
                mealTypeName[i++] = m;
                System.out.println(i + ") "+ m.getMeal());
            }
            else {
                System.out.println("Too Many Meal Types " + m.getMeal() + " not included.");
            }
        }
        System.out.print("Please Enter your Choice: ");
        ans = keyboard.next().charAt(0);
        if (Character.isDigit(ans)) {
            ansNum = Character.getNumericValue(ans);
            mealType = mealTypeName[ansNum-1];
        }
        else {
            mealType = MealType.DINNER;
            System.out.println("Invalid Meal Type " + ans + ", defaulted to " + mealType.getMeal() + ".");
        }
        mealsArray.printMealsByType(mealType);
    }
    private static void searchByName() {
        keyboard.nextLine();
        System.out.print("Please Enter Value: ");
        String ans = keyboard.nextLine();
        mealsArray.printByNameSearch(ans);
    }
}