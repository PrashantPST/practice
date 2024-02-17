package design.lld.instagram.dtos;

public class PostDto {
    private String id;
    private String caption;
    private String imageUrl;
    private String videoUrl;
    private String link;
    private String content;
    // Depending on your requirements, you might also want to include:
    // private String userId; // The ID of the user who created the post
    // private Date createdAt; // The creation date of the post

    // Constructors, getters, and setters

    public PostDto() {
        // Default constructor
    }

    // You might want to add a constructor that takes a Post entity and initializes the DTO fields

    // Getters and setters for all fields
    // ...
}
