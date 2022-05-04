# Music Visualiser Project

| Name | Student Number |
|-----------|-----------|
|Ronan Singpurwala | C20391216 |
|Kieran Silada | C20483514 |
|Aman Song | C20325896 |
|Eoin O'Toole Carrick | C20310571 |

# Description Of The Assignment
In this assignment we have 4 visualisations using java processing. Each section was made by a different person in the group. Our visualisations respond to the amplitude of the music and frequency by changing colours, sizes of shapes and so on. To extract audio from an mp3 file, we used the audio minim library. 

# Instructions
- Run the main.java file in the ie\tudublin folder.
- Space bar is to pause/unpause audio.
- You can switch through the visualisations using the 1, 2, 3 and 4 keys.
- Note that visualisations switch automatically at certain parts of the song which suits that particular visualisation best.

# How It Works
Each visualisation has its own class and they are all in the same package. Enemy.java is the driver file for this project. We created 4 objects for the visualisation. We used inheritance to use methods like "calculateAverageAmplitude" from the Visual class. Using the minim we loaded in the Enemy.mp3 which is done in the setup method.

```java
Eoin Eoin = new Eoin(1920);
Kieran Kieran = new Kieran(1024);
Ronan Ronan = new Ronan();
Aman Aman = new Aman();
```
each visualisation has a method named "render" which displays the visuals. The objects created in the Heathens class would call the render method inside a switch statement statement so the user can switch between which each visualisation.

```java
switch(mode)
{
    case 1: //Eoin SECTION 1
    {
        Eoin.render(this);
        break;
    }

    case 2: //Kieran SECTION 2
    {
        Kieran.render(this);
        break;
    }

    case 3: //Aman SECTION 3
    {
        Aman.render(this);
        break;
    }

    case 4: //Ronan SECTION 4
    {
        Ronan.render(this);
        break;
    }
}
```





# What Are We Most Proud Of In The Assignment
In our opinion, we are most proud of our 5th visualisation near the end of our song. It is the amalgamation of all our hard work and research into this project. Combining aspects of each section and having it work harmoniously is proof of how well our visuals go together. Each combined aspect complement with Eoin's colorful circles, Kieran's complex 3D ring visualiser, Aman's fluttering eye shape Sharingan and Ronan's reactive border and prisms


# Images From Visualiser
### Visualisation 1
![An image](images/Image1.png)

### Visualisation 2
![Aman's section](images/Aman.jpg)

### Visualisation 3
![image](images/Ronan.jpg)
