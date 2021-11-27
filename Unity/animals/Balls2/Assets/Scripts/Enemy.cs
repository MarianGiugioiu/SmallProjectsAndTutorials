using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    private Rigidbody enemyRB;
    public float speed = 3.0f;
    private GameObject player;
    // Start is called before the first frame update
    void Start()
    {
        enemyRB = GetComponent<Rigidbody>();
        player = GameObject.Find("Player");
    }

    // Update is called once per frame
    void Update()
    {
        enemyRB.AddForce((player.transform.position - transform.position).normalized * speed);
        if(transform.position.y < -10)
        {
            Destroy(gameObject);
        }
    }
}
