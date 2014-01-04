using UnityEngine;

/// <summary>
/// Enemy generic behavior
/// </summary>
public class EnemyScript2 : MonoBehaviour
{
    private bool hasSpawn;
    private WeaponScript[] weapons;

    void Awake()
    {
        // Retrieve scripts to disable when not spawn
        weapons = GetComponentsInChildren<WeaponScript>();
    }

    void Start()
    {

        hasSpawn = false;

        // Disable everything
        // -- collider
        collider2D.enabled = false;

        foreach (WeaponScript weapon in weapons)
        {
            weapon.enabled = false;
        }

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
                foreach (WeaponScript weapon in weapons)
                {
                    if (weapon != null && weapon.enabled && weapon.CanAttack)
                    {
                        weapon.Attack(true);
                    }
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
        foreach (WeaponScript weapon in weapons)
        {
            weapon.enabled = true;
        }
    }

    private void Despawn()
    {
        hasSpawn = false;
        // -- collider
        collider2D.enabled = false;
        foreach (WeaponScript weapon in weapons)
        {
            weapon.enabled = false;
        }
    }
}