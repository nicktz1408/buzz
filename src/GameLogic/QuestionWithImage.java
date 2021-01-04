package GameLogic;

/**
 * Class for the Question that has an associated Image
 */
public class QuestionWithImage extends Question {
    private String imagePath;

    /**
     * Initializes the object from a QuestionWithImage.Builder class
     * @param builder the Builder to build our QuestionWithImage from
     */
    public QuestionWithImage(Builder builder) {
        super(builder);

        this.setImagePath(builder.imagePath);
    }

    /**
     *
     * @return the image path of the associated image
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     *
     * @param imagePath the image path of the associated image to be set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     *
     * @return a new Builder object to be used to create a QuestionWithImage object
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Inner class for the Class' builder
     */
    public static class Builder extends Question.Builder<Builder> {
        private String imagePath;

        /**
         *
         * @return a reference to itself)
         */
        @Override
        public Builder getThis() {
            return this;
        }

        /**
         * Constructs the imagePath
         * @param imagePath the ’given imagePath
         * @return the current Builder reference to allow stacking together function calls
         */
        public Builder setImagePath(String imagePath) {
            // question.setImagePath(imagePath);
            this.imagePath = imagePath;

            return this;
        }

        /**
         *
         * @return the constructed QuestionWithImage
         */
        public QuestionWithImage build() {
            return new QuestionWithImage(this);
        }
    }
}
