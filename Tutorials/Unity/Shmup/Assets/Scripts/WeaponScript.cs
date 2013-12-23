using UnityEngine;
using System.Collections;

// Launch projectile
public class WeaponScript : MonoBehaviour 
{
	//------------------------------
	// 1 - Designer variables
	//------------------------------

	/// Projectile prefab for shooting
	public Transform shotPrefab;

	/// Cooldown in seconds between two shots
	public float shootingRate = 0.25f;

	//------------------------------
	// 2 - Cooldown
	//------------------------------

	private float shootCooldown;
	
	void Start() 
	{
		shootCooldown = 0f;
	}

	void Update() 
	{
		if(shootCooldown > 0)
		{
			shootCooldown -= Time.deltaTime;
		}
	}

	//------------------------------
	// 3 - Shooting from another script
	//------------------------------

	/// Create a new projectile if possible
	public void Attack(bool isEnemy)
	{
		if(CanAttack)
		{
			shootCooldown = shootingRate;

			// Create a new shot
			var shotTransform = Instantiate(shotPrefab) as Transform;

			// Assign position
			shotTransform.position = transform.position;

			// The is enemy property
			ShotScript shot =
				shotTransform.gameObject.GetComponent<ShotScript>();
			if(shot != null)
			{
				shot.isEnemyShot = isEnemy;
			}

			// Make the weapon shot always towards it
			MoveScript move =
				shotTransform.gameObject.GetComponent<MoveScript>();
			if(move != null)
			{
				// towards in 2D space is the right of the sprite
				move.direction = this.transform.right;
			}
		}
	}

	// Is the weapon ready to create a new projectile?
	public bool CanAttack
	{
		get
		{
			return shootCooldown <= 0f;
		}
	}
}