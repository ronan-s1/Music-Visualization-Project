# Music Visualiser Project

| Name | Student Number |
|-----------|-----------|
|Ronan Singpurwala | C20391216 |
|Keiran Retardo Silada | C20483514 |
|Aman Song | C20325896 |
|Eoin O'Toole Carrick | C20310571 |

# Description Of The Assignment
In this assignment we have 4 visualisations using java processing. Each section was made by a different person in the group. Our visualisations respond to the amplitude of the music and frequency by changing colours, sizes of shapes and so on. To extract audio from an mp3 file, we used the audio minum library. 

# Instructions
- Run the main.java file in the C20391216 folder.
- Space bar is to pause/unpause audio.
- You can switch through the visualisations using the 1, 2, 3 and 4 keys.
- Note that visualisations switch automatically at certain parts of the song which suits that particular visualisation best.

# How It Works
Each visualisation has its own class and they are all in the same package. Heathens.java is the driver file for this project. We created 4 objects for the visualisation. We used inheritance to use methods like "calculateAverageAmplitude" from the Visual class. Using the minum we loaded in the heathens.mp3 which is done in the setup method.

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
We are all proud of the amount of effort and research we put into this assignment. We believe our visuals go well together with our song choice so when the beat drops it suits the visualiser perfectly and that overall makes watching it an overall better experience.

In the Ronan class, we were able to have particles in the background along with rotating 3d objects which also changes colour. We are really proud of because it goes really well with the music we chose.

# Images From Visualiser
### Visualtisation 1
![An image](images/Image1.png)

### Visualtisation 2
![Aman's section](images/Aman.jpg)

### Visualtisation 3
![image](images/Ronan.jpg)
