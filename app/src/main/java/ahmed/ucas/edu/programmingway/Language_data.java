package ahmed.ucas.edu.programmingway;


import androidx.annotation.Keep;

@Keep
public class Language_data {

    private String arabicCourse;
    private String docId;
    private String englishCourse;
    private String imageUrl;
    private String languageDescription;
    private String languageName;
    private String languageType;
    private String premiumCourse;

    public void setArabicCourse(String arabicCourse) {
        this.arabicCourse = arabicCourse;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setEnglishCourse(String englishCourse) {
        this.englishCourse = englishCourse;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public void setPremiumCourse(String premiumCourse) {
        this.premiumCourse = premiumCourse;
    }


    public String getArabicCourse() {
        return arabicCourse;
    }

    public String getDocId() {
        return docId;
    }

    public String getEnglishCourse() {
        return englishCourse;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLanguageDescription() {
        return languageDescription;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageType() {
        return languageType;
    }

    public String getPremiumCourse() {
        return premiumCourse;
    }


}
