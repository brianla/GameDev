using UnityEngine;
using System.Collections;

public class OneWayPlatScript : MonoBehaviour {
	
	private Rigidbody2D body;
	private Collider2D plat;
	private PlayerScript player;
	
	void Start() 
	{
		body = this.gameObject.GetComponent<Rigidbody2D>();
		player = this.gameObject.GetComponent<PlayerScript>();
		plat = null;
	}
	
	void OnTriggerEnter2D(Collider2D other) 
	{
		if(other.gameObject.CompareTag("OneWayPlatform") && body.velocity.y <= 0) {
			plat = other;
			plat.isTrigger = false;
			body.velocity = new Vector3(body.velocity.x, 0);
		}
	}

	void FixedUpdate() 
	{
		if((plat != null) && !player.isGrounded)
			plat.isTrigger = true;
	}

}
