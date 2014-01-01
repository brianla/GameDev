using UnityEngine;
using System.Collections;

public class OneWayPlatScript : MonoBehaviour {
	
	private Rigidbody2D body;
	private Collider2D plat1;
	private Collider2D plat2;
	private PlayerScript player;
	
	void Start() 
	{
		body = this.gameObject.GetComponent<Rigidbody2D>();
		player = this.gameObject.GetComponent<PlayerScript>();
		plat1 = null;
		plat2 = null;
	}
	
	void OnTriggerEnter2D(Collider2D other) 
	{
		if(other.gameObject.CompareTag("OneWayPlatform") && body.velocity.y <= 0) {
			if(plat1 != null) {
				plat2 = plat1;
			}
			plat1 = other;
			plat1.isTrigger = false;
			body.velocity = new Vector3(body.velocity.x, 0);
		}
	}

	void FixedUpdate() 
	{
		if((plat1 != null) && !(player.Grounded()))
			plat1.isTrigger = true;
		if((plat2 != null) && !(player.Grounded()))
			plat2.isTrigger = true;
	}

}
