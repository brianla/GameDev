using UnityEngine;

/// <summary>
/// Handle hitpoints and damages
/// </summary>
public class HealthScript : MonoBehaviour
{
    /// <summary>
    /// Total hitpoints
    /// </summary>
    public int hp = 2;

    /// <summary>
    /// Enemy or player?
    /// </summary>
    public bool isEnemy = true;



// Knockback distance
	public float knockbackX = 100f;
	public float knockbackY = 1000f;
	
	public float invuln = 2f;
	public float invAlpha = 0.5f;

	private bool playerHit = false;
	private float timeHit;



    void OnTriggerEnter2D(Collider2D collider)
    {
        // Is this a shot?
        ShotScript shot = collider.gameObject.GetComponent<ShotScript>();
        if (shot != null)
        {
            // Avoid friendly fire
            if (shot.isEnemyShot != isEnemy)
            {
				// Destroy the shot
				// Remember to always target the game object,
				// otherwise you will just remove the script.
				Destroy(shot.gameObject);

				if (isEnemy || (!isEnemy && !playerHit)) {
	                hp -= shot.damage;

	                if (hp <= 0)
	                {

	                    // 'Splosion!
	                  //  SpecialEffectsHelper.Instance.Explosion(transform.position);
	                  //  SoundEffectsHelper.Instance.MakeExplosionSound();

	                    // Dead!
	                    Destroy(gameObject);
					}

	// Knockback effect to Player
					if (!isEnemy) {
						
						playerHit = true;
						timeHit = Time.time;
						
						// If shot is moving left, then knockback to the left
						if (shot.rigidbody2D.velocity.x < 0)
							Knockback(false);
						else
							Knockback(true);
					}
				}
            }
        }
    }

	void Knockback(bool isRight) {
		Vector3 horizontalKB = transform.right;
		
		if (!isRight) {
			horizontalKB *= -1;
		}
		
		this.rigidbody2D.AddForce((horizontalKB * knockbackX) + (transform.up * knockbackY));
	}

	void Invulnerable() {
		SpriteRenderer renderer = this.gameObject.GetComponent<SpriteRenderer>();

		if ( (((Time.time - timeHit) * 100) %  50) < 25) {
			renderer.color = new Color(1f, 1f, 1f, invAlpha);
		}
		else {
			renderer.color = new Color(1f, 1f, 1f, 1f);
		}
	}

	void Update() {
		if (playerHit && (Time.time - timeHit) >= invuln) {
			playerHit = false;
		}
		else if (playerHit) {
			Invulnerable();
		}
	}
}