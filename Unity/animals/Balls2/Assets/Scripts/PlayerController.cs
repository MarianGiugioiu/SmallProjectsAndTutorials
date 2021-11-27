using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float speed = 5;
    private Rigidbody playerRB;
    private GameObject focalPoint;
    public bool hasPowerUp = false;
    private float powerUpStrength = 15.0f;
    public GameObject PowerUpIndicator;
    // Start is called before the first frame update
    void Start()
    {
        playerRB = GetComponent<Rigidbody>();
        focalPoint = GameObject.Find("FocalPoint");
    }

    // Update is called once per frame
    void Update()
    {
        float forwardInput = Input.GetAxis("Vertical");
        playerRB.AddForce(focalPoint.transform.forward * forwardInput * speed);
        PowerUpIndicator.gameObject.transform.position = transform.position;

    }
    
    private void OnTriggerEnter(Collider other)
    {
        if(other.CompareTag("PowerUp"))
        {
            Destroy(other.gameObject);
            hasPowerUp = true;
            PowerUpIndicator.gameObject.SetActive(true);
            StartCoroutine(PowerUpCountdownRoutine());
        }
    }
    private void OnCollisionEnter(Collision collision)
    {
        if(collision.gameObject.CompareTag("Enemy") && hasPowerUp)
        {
            Rigidbody enemyRigidbody = collision.gameObject.GetComponent<Rigidbody>();
            Vector3 awayFromPlayer = collision.gameObject.transform.position - transform.position;
            enemyRigidbody.AddForce(awayFromPlayer * powerUpStrength, ForceMode.Impulse);
            Debug.Log("Collided with " + collision.gameObject.name + "With power up set to " + hasPowerUp);
        }
    }
    IEnumerator PowerUpCountdownRoutine()
    {
        yield return new WaitForSeconds(7);
        hasPowerUp = false;
        PowerUpIndicator.gameObject.SetActive(false);
    }
}
