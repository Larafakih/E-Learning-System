package Model;

import java.util.ArrayList;

public class QuestionStore {

    @Override
    public String toString() {
        return "QuestionStore{" + "questionList=" + questionList + '}';
    }

    ArrayList<Question> questionList;

    public QuestionStore() {
        questionList = new ArrayList<>();
    }

    public void addQuestion(Question q) {
        questionList.add(q);
    }

    public Question getQuestion(int pos) {
        return questionList.get(pos);
    }

    public Question getQuestionByQno(int qno) {
        for (Question ques : questionList) {
            if (ques.getQnb() == qno) {
                return ques;
            }
        }
        return null;
    }

    public void removeQuestion(int pos) {
        questionList.remove(pos);
    }

    public void setQuestionAt(int pos, Question q) {
        questionList.add(pos, q);
    }

    public ArrayList<Question> getAllQuestions() {
        return questionList;
    }

    public int getCount() {
        return questionList.size();
    }
}
