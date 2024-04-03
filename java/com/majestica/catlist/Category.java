package com.majestica.catlist;

public class Category
{
    private final String image;
    private final String category;

    public Category(final String image, final String category)
    {
        if(image == null || image.isEmpty())
        {
            throw new IllegalArgumentException("No name is set for image");
        }
        if(category == null || category.isEmpty())
        {
            throw new IllegalArgumentException("No name is set for the category");
        }
        this.image = image;
        this.category = category;
    }

    public String getImage()
    {
        return image;
    }

    public String getCategory()
    {
        return category;
    }
}
