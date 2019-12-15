/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Le Quang Hien
  ID: s3695516
  Created  date: 13/12/2019
  Last modified: 13/12/2019
  Acknowledgement:
*/

package model;

public class Card
{
    public int number;
    private String image;
    private int position;
    public boolean isRemoved = false;

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
