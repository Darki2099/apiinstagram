package Modelo;

public class UserMedia {
    private String mediaId;
    private String imageUrl;
    private String caption;

    public UserMedia(String mediaId, String imageUrl, String caption) {
        this.mediaId = mediaId;
        this.imageUrl = imageUrl;
        this.caption = caption;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCaption() {
        return caption;
    }
}


