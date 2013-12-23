using UnityEngine;
using System.Collections;

public class HealthScript : MonoBehaviour 
{
	public int hp = 2;

	public bool isEnemy = true;

	// Use this for initialization
	void OnTriggerEnter2D(Collider2D collider)
	{
		ShotScript shot = collider.gameObject.GetComponent<ShotScript>();
		if(shot != null)
		{
			if(shot.isEnemyShot != isEnemy)
			{
				hp -= shot.damage;

				Destroy(shot.gameObject);

				if(hp <= 0)
				{
					// 'Splosion!
					SpecialEffectsHelper.Instance.Explosion(transform.position);
					SoundEffectsHelper.Instance.MakeExplosionSound();

					// Dead!
					Destroy(gameObject);
				}
			}
		}

	}
}
