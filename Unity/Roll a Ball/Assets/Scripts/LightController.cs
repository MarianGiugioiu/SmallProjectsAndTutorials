using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LightController : MonoBehaviour
{
    private Light myLight;
    // Start is called before the first frame update
    void Start()
    {
        myLight.color = Color.red;
        myLight.intensity = 0;
        myLight = GetComponent<Light>();
    }

    // Update is called once per frame
    void Update()
    {
        myLight.intensity = Mathf.Lerp(myLight.intensity, 8f, 0.5f);
        if (Input.GetKeyUp(KeyCode.Space))
        {
            myLight.enabled = !myLight.enabled;
        }
    }
}
