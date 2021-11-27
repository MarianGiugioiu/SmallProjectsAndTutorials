using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class GameManager : MonoBehaviour
{
    public List<GameObject> targets;
    public TextMeshProUGUI scoreText;
    private int score;
    private float spawnRate = 1.0f;
    // Start is called before the first frame update
    void Start()
    {
        score = 0;
        StartCoroutine(spawnTarget());
        UpdateScore(0);
    }

    // Update is called once per frame
    void Update()
    {
     
    }
    IEnumerator spawnTarget()
    {
        while (true)
        {
            yield return new WaitForSeconds(spawnRate);
            int index = Random.Range(0, targets.Count);
            Instantiate(targets[index]);
        }
    }

    public void UpdateScore(int scoreToAdd)
    {
        score += scoreToAdd;
        scoreText.text = "Score: " + score;
    }
}
