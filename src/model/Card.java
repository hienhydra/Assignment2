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
    private int cardId;
    private String image;
    private int position;
    public boolean isRemoved = false;

    public Card() {}
    public Card(int number, int position)
    {
        this.position = position;
        this.cardId = number;
        image = "File:src/img/" + number + ".png";
    }

    public int getPosition()
    {
        return position;
    }

    public String getImage()
    {
        return image;
    }

    public int getCardID()
    {
        return cardId;
    }

    public boolean compareTo(Card anotherCard)
    {
        return this.cardId == anotherCard.cardId;
    }
}
