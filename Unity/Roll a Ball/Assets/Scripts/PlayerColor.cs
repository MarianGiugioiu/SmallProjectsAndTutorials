using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerColor : MonoBehaviour
{
    int x = 3;
    float coffeeTemperature = 85.0f;
    float hotLimitTemperature = 70.0f;
    float coldLimitTemperature = 40.0f;
    void Start()
    {
        coffeeTemperature = 85.0f;
        hotLimitTemperature = 70.0f;
        coldLimitTemperature = 40.0f;
        Debug.Log(transform.position.y);
        string[] strings = new string[3];

        strings[0] = "First string";
        strings[1] = "Second string";
        strings[2] = "Third string";

        foreach (string item in strings)
        {
            print(item);
        }
    }
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Space))
            TemperatureTest();

        coffeeTemperature -= Time.deltaTime * 5f;
        if (Input.GetKeyDown(KeyCode.R))
        {
            GetComponent<Renderer>().material.color = Color.red;
        }
        if (Input.GetKeyDown(KeyCode.G))
        {
            GetComponent<Renderer>().material.color = Color.green;
        }
        if (Input.GetKeyDown(KeyCode.B))
        {
            GetComponent<Renderer>().material.color = Color.blue;
        }
    }
    void TemperatureTest()
    {
        // If the coffee's temperature is greater than the hottest drinking temperature...
        if (coffeeTemperature > hotLimitTemperature)
        {
            // ... do this.
            print("Coffee is too hot.");
        }
        // If it isn't, but the coffee temperature is less than the coldest drinking temperature...
        else if (coffeeTemperature < coldLimitTemperature)
        {
            // ... do this.
            print("Coffee is too cold.");
        }
        // If it is neither of those then...
        else
        {
            // ... do this.
            print("Coffee is just right.");
        }
    }
}
