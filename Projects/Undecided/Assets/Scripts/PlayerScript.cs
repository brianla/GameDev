using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {

    private bool isGrounded = false;
    private Vector2 movement, jumpMovement;
    public float jumpHeight = 1000f;
    public float speed = 8.0f;
    private bool facingRight = true;

    void FixedUpdate()
    {
        float inputX = Input.GetAxis("Horizontal");

        isGrounded = (rigidbody2D.velocity.y == 0);

        transform.position += transform.right * inputX * speed * Time.deltaTime;

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
}
