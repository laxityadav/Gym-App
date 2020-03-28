package com.example.gymproject;

import java.util.ArrayList;

public class Utils {

    private static ArrayList<GymTraining> allTrainings;
    private static ArrayList<Plan> usersPlan;

    public Utils() {
    }

    public static void initialiseAll() {
        if (null == allTrainings) {
            allTrainings = new ArrayList<>();

            int id=0;
            String name="Squat";
            String shortDesc="The squat is performed by squatting down with a weight held across the upper back under neck and standing up straight again.";
            String longDesc="The squat is performed by squatting down with a weight held across the upper back under neck and standing up straight again.";
            String url="https://image.shutterstock.com/image-photo/deep-squat-part-young-man-600w-390236356.jpg";
            GymTraining gymTraining = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining);

            name="Leg Press";
            shortDesc="The leg press is performed while seated by pushing a weight away from the body with the feet.";
            longDesc="The leg press is performed while seated by pushing a weight away from the body with the feet.";
            url="https://image.shutterstock.com/image-photo/man-gym-training-leg-press-600w-361134620.jpg";
            GymTraining gymTraining1 = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining1);

            name="Deadlift";
            shortDesc="The deadlift is performed by squatting down and lifting a weight off the floor with the hand until standing up straight again.";
            longDesc="The deadlift is performed by squatting down and lifting a weight off the floor with the hand until standing up straight again.";
            url="https://image.shutterstock.com/image-photo/sporty-man-woman-about-lift-600w-791792701.jpg";
            GymTraining gymTraining2 = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining2);

            name="Bench Press";
            shortDesc="The bench press or dumbbell bench-press is performed while lying face up on a bench, by pushing a weight away from the chest.";
            longDesc="The bench press or dumbbell bench-press is performed while lying face up on a bench, by pushing a weight away from the chest.";
            url="https://image.shutterstock.com/image-photo/weight-lifter-bench-press-lifting-600w-245915032.jpg";
            GymTraining gymTraining3 = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining3);

            name="Chest Fly";
            shortDesc="The chest fly is performed while lying face up on a bench or standing up, with arms outspread holding weights, by bringing the arms together above the chest.";
            longDesc="The chest fly is performed while lying face up on a bench or standing up, with arms outspread holding weights, by bringing the arms together above the chest.";
            url="https://image.shutterstock.com/image-photo/young-man-doing-dumbbell-fly-600w-597953516.jpg";
            GymTraining gymTraining4 = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining4);

            name="Shoulder Press";
            shortDesc="The shoulder press is performed while seated, or standing by lowering a weight held above the head to just above the shoulders, and then raising it again.";
            longDesc="The shoulder press is performed while seated, or standing by lowering a weight held above the head to just above the shoulders, and then raising it again.";
            url="https://image.shutterstock.com/image-photo/muscular-man-training-his-shoulders-600w-336330470.jpg";
            GymTraining gymTraining5 = new GymTraining(++id, name, shortDesc, longDesc, url);
            allTrainings.add(gymTraining5);

        }
        if (null == usersPlan) {
            usersPlan = new ArrayList<>();
        }

    }

    public static ArrayList<GymTraining> getAllTrainings() {
        return allTrainings;
    }

    public static void setAllTrainings(ArrayList<GymTraining> allTrainings) {
        Utils.allTrainings = allTrainings;
    }

    public static ArrayList<Plan> getUsersPlan() {
        return usersPlan;
    }

    public static void setUsersPlan(ArrayList<Plan> usersPlan) {
        Utils.usersPlan = usersPlan;
    }

    public static boolean addToUserPlan(Plan plan) {
        return usersPlan.add(plan);
    }

    public static boolean removeUserPlan(Plan plan){
        return usersPlan.remove(plan);
    }
}
