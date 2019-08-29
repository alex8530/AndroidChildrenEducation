package com.example.mrzero.androidadkyaaapp3.model.getStatistics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistic {
    @SerializedName("general_student_rate")
    @Expose
    private String generalStudentRate;
    @SerializedName("general_student_rate_text")
    @Expose
    private String generalStudentRateText;
    @SerializedName("sitting_interacting_rate")
    @Expose
    private String sittingInteractingRate;
    @SerializedName("daily_sections_resolved")
    @Expose
    private Integer dailySectionsResolved;
    @SerializedName("daily_exercises_resolved")
    @Expose
    private Integer dailyExercisesResolved;
    @SerializedName("daily_correct_answers_rate")
    @Expose
    private Integer dailyCorrectAnswersRate;
    @SerializedName("weekly_sections_resolved")
    @Expose
    private Integer weeklySectionsResolved;
    @SerializedName("week_exercises")
    @Expose
    private Integer weekExercises;
    @SerializedName("weekly_correct_answers_rate")
    @Expose
    private Integer weeklyCorrectAnswersRate;
    @SerializedName("monthly_sections_resolved")
    @Expose
    private Integer monthlySectionsResolved;
    @SerializedName("monthly_exercises_resolved")
    @Expose
    private Integer monthlyExercisesResolved;
    @SerializedName("monthly_correct_answers_rate")
    @Expose
    private Integer monthlyCorrectAnswersRate;
    @SerializedName("total_section")
    @Expose
    private Integer totalSection;
    @SerializedName("total_exercises")
    @Expose
    private Integer totalExercises;
    @SerializedName("total_average")
    @Expose
    private Integer totalAverage;

    public String getGeneralStudentRate() {
        return generalStudentRate;
    }

    public void setGeneralStudentRate(String generalStudentRate) {
        this.generalStudentRate = generalStudentRate;
    }

    public String getGeneralStudentRateText() {
        return generalStudentRateText;
    }

    public void setGeneralStudentRateText(String generalStudentRateText) {
        this.generalStudentRateText = generalStudentRateText;
    }

    public String getSittingInteractingRate() {
        return sittingInteractingRate;
    }

    public void setSittingInteractingRate(String sittingInteractingRate) {
        this.sittingInteractingRate = sittingInteractingRate;
    }

    public Integer getDailySectionsResolved() {
        return dailySectionsResolved;
    }

    public void setDailySectionsResolved(Integer dailySectionsResolved) {
        this.dailySectionsResolved = dailySectionsResolved;
    }

    public Integer getDailyExercisesResolved() {
        return dailyExercisesResolved;
    }

    public void setDailyExercisesResolved(Integer dailyExercisesResolved) {
        this.dailyExercisesResolved = dailyExercisesResolved;
    }

    public Integer getDailyCorrectAnswersRate() {
        return dailyCorrectAnswersRate;
    }

    public void setDailyCorrectAnswersRate(Integer dailyCorrectAnswersRate) {
        this.dailyCorrectAnswersRate = dailyCorrectAnswersRate;
    }

    public Integer getWeeklySectionsResolved() {
        return weeklySectionsResolved;
    }

    public void setWeeklySectionsResolved(Integer weeklySectionsResolved) {
        this.weeklySectionsResolved = weeklySectionsResolved;
    }

    public Integer getWeekExercises() {
        return weekExercises;
    }

    public void setWeekExercises(Integer weekExercises) {
        this.weekExercises = weekExercises;
    }

    public Integer getWeeklyCorrectAnswersRate() {
        return weeklyCorrectAnswersRate;
    }

    public void setWeeklyCorrectAnswersRate(Integer weeklyCorrectAnswersRate) {
        this.weeklyCorrectAnswersRate = weeklyCorrectAnswersRate;
    }

    public Integer getMonthlySectionsResolved() {
        return monthlySectionsResolved;
    }

    public void setMonthlySectionsResolved(Integer monthlySectionsResolved) {
        this.monthlySectionsResolved = monthlySectionsResolved;
    }

    public Integer getMonthlyExercisesResolved() {
        return monthlyExercisesResolved;
    }

    public void setMonthlyExercisesResolved(Integer monthlyExercisesResolved) {
        this.monthlyExercisesResolved = monthlyExercisesResolved;
    }

    public Integer getMonthlyCorrectAnswersRate() {
        return monthlyCorrectAnswersRate;
    }

    public void setMonthlyCorrectAnswersRate(Integer monthlyCorrectAnswersRate) {
        this.monthlyCorrectAnswersRate = monthlyCorrectAnswersRate;
    }

    public Integer getTotalSection() {
        return totalSection;
    }

    public void setTotalSection(Integer totalSection) {
        this.totalSection = totalSection;
    }

    public Integer getTotalExercises() {
        return totalExercises;
    }

    public void setTotalExercises(Integer totalExercises) {
        this.totalExercises = totalExercises;
    }

    public Integer getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Integer totalAverage) {
        this.totalAverage = totalAverage;
    }
}
