using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {

    private bool isGrounded = false;
    private Vector2 movement, jumpMovement;
    public float jumpHeight = 1000f;
    public float speed = 8.0f;
    private bool facingRight = true;

	private bool onHillLeft = false;
	private bool onHillRight = false;
	private float oldX, distX;
    public bool redKey = false;

    void FixedUpdate()
    {
        float inputX = Input.GetAxis("Horizontal");

        isGrounded = (rigidbody2D.velocity.y == 0);

		oldX = transform.position.x;
        transform.position += transform.right * inputX * speed * Time.deltaTime;

		distX = transform.position.x - oldX;

		if((onHillLeft && distX < 0) || (onHillRight && distX > 0))
		{

			if(distX > 0) 
				distX = -distX;

			transform.position = new Vector3(
				transform.position.x, 
				transform.position.y + distX,
				transform.position.z);
		}



		if(Input.GetButton("Jump") && isGrounded){
			Jump();
		}

        if (inputX > 0 && !facingRight)
            Flip();
        else if (inputX < 0 && facingRight)
            Flip();

	}

    void Update()
    {
        bool shoot = Input.GetButtonDown("Fire1");
        shoot |= Input.GetButtonDown("Fire2");

        if (shoot)
        {
            WeaponScript weapon = GetComponent<WeaponScript>();
            if (weapon != null)
            {
                // false because the player is not an enemy
                weapon.Attack(false);
              //  SoundEffectsHelper.Instance.MakePlayerShotSound();
            }
        }
    }
	
	void Jump()
	{
        if (!isGrounded)
        {
            return;
        }
        rigidbody2D.AddForce(transform.up * jumpHeight);
    }

    void Flip()
    {

        facingRight = !facingRight;

        Vector3 theScale = transform.localScale;
        theScale.x *= -1;
        transform.localScale = theScale;
    }

	void OnCollisionEnter2D(Collision2D c)
	{
		if(c.gameObject.CompareTag("HillLeft"))
		{
			onHillLeft = true;
		}
		else if(c.gameObject.CompareTag("HillRight"))
		{
			onHillRight = true;
		}

	}

	void OnCollisionExit2D(Collision2D c)
	{
		if(c.gameObject.CompareTag("HillLeft"))
		{
			onHillLeft = false;
		}
		else if(c.gameObject.CompareTag("HillRight"))
		{
			onHillRight = false;
		}
	}

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.CompareTag("RedKey"))
        {
            redKey = true;
			Destroy(other.gameObject);
        }
        if (other.gameObject.CompareTag("RedDoor"))
        {
            if (redKey)
            {
                Destroy(other.gameObject.transform.parent.gameObject);
            }
        }
    }
	public bool Grounded()
	{
		return isGrounded;
	}
}
