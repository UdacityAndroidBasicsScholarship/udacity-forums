package com.example.android.udacityforum;

class QuestionFormat {
    private String quesHeading;
    private String quesContent;
    public QuestionFormat(String heading,String content){
        this.quesContent=content;
        this.quesHeading=heading;
    }

    public String getQuesHeading() {
        return quesHeading;
    }

    public String getQuesContent() {
        return quesContent;
    }
}
