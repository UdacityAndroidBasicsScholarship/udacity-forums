package com.example.android.udacityforum;

class QuestionFormat {
    private String quesHeading;
    private String quesContent;
    private long questionID;


    public QuestionFormat(String quesHeading, String quesContent, long questionID) {
        this.quesHeading = quesHeading;
        this.quesContent = quesContent;
        this.questionID = questionID;
    }

    public void setQuesHeading(String quesHeading) {
        this.quesHeading = quesHeading;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    public long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(long questionID) {
        this.questionID = questionID;
    }

    public QuestionFormat(String heading, String content) {
        this.quesContent = content;
        this.quesHeading = heading;

    }

    public String getQuesHeading() {
        return quesHeading;
    }

    public String getQuesContent() {
        return quesContent;
    }
}
