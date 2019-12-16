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
    // declare properties of the card
    private int cardId;
    private String image;
    private int position;
    public boolean isRemoved = false;

    // constructor with id and position on the game
    public Card(int id, int position)
    {
        this.position = position;
        this.cardId = id;
        image = "File:src/img/" + id + ".png";
    }

    // this method is to get the position of the card
    public int getPosition()
    {
        return position;
    }

    // this method is to get the image of the card
    public String getImage()
    {
        return image;
    }

    // this method is to compare two card IDs
    public boolean compareTo(Card anotherCard)
    {
        return this.cardId == anotherCard.cardId;
    }
}
