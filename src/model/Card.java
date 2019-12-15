package model;

public class Card
{
    public int number;
    private String image;
    private int position;

    public Card() {}
    public Card(int number, int position)
    {
        this.position = position;
        this.number = number;
        image = "File:src/img/" + number + ".png";
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }

    public String getImage()
    {
        return image;
    }

    public boolean compareTo(Card anotherCard)
    {
        return this.number == anotherCard.number;
    }
}
