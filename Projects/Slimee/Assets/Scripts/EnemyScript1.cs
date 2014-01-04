using UnityEngine;

/// <summary>
/// Enemy generic behavior
/// </summary>
public class EnemyScript1 : MonoBehaviour
{
    private bool hasSpawn;
    public float time = 3f;
    private float prevTime;
    private MoveScript moveScript;
    private float timeDiff;

    void Awake()
    {
        // Retrieve scripts to disable when not spawn
        moveScript = this.GetComponent<MoveScript>() as MoveScript;
    }

    void Start()
    {

        hasSpawn = false;

        // Disable everything
        // -- collider
        collider2D.enabled = false;
        // -- Moving
        moveScript.enabled = false;

        prevTime = Time.time;
    }

    void Update()
    {

        if (hasSpawn == false)
        {
            if (renderer.IsVisibleFrom(Camera.main))
            {
                Spawn();
            }
        }
        else
        {
            if (renderer.IsVisibleFrom(Camera.main) == false)
            {
                Despawn();
            }
            else
            {

                float end = Time.time;
                timeDiff += end - prevTime;
                prevTime = end;
                if (timeDiff >= time)
                {
                    timeDiff -= time;
                    moveScript.direction = new Vector2(-moveScript.direction.x, moveScript.direction.y);
                }
            }
        }
    }

    private void Spawn()
    {

        hasSpawn = true;

        // Enable everything
        // -- Collider
        collider2D.enabled = true;
        // -- Moving
        moveScript.enabled = true;
        prevTime = Time.time;
    }

    private void Despawn()
    {
        hasSpawn = false;
        // -- collider
        collider2D.enabled = false;
        // -- Moving
        rigidbody2D.velocity = new Vector2(0, 0);
        moveScript.enabled = false;
    }
}