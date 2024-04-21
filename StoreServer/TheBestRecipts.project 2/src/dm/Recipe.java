package dm;


import java.util.ArrayList;


public class Recipe implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String chef;
    private Long id;
    private String title;
    private String description;
    private ArrayList<Double> ratings;

    public double getMeanRating() {
        return meanRating;
    }

    public void setMeanRating(double meanRating) {
        this.meanRating = meanRating;
    }

    private double meanRating;
    public Recipe() {}

    public Recipe(Long id, String chef, String title, String description) {
        this.id = id;
        this.chef = chef;
        this.title = title;
        this.description = description;
        this.ratings = new ArrayList<>();
    }


    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Recipe other = (Recipe) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (chef == null) {
            if (other.chef != null)
                return false;
        } else if (!chef.equals(other.chef))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "chef='" + chef + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public ArrayList<Double> getRatings() {
            if (ratings == null) {
                ratings = new ArrayList<>();
            }
            return ratings;
    }
}
