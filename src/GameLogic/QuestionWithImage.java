package GameLogic;

public class QuestionWithImage extends Question {
    private String imagePath;

    public QuestionWithImage(Builder builder) {
        super(builder);
        this.imagePath = builder.imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Question.Builder<Builder> {
        private String imagePath;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder setImagePath(String imagePath) {
            // question.setImagePath(imagePath);
            this.imagePath = imagePath;

            return this;
        }

        public QuestionWithImage build() {
            return new QuestionWithImage(this);
        }
    }
}
