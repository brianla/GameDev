using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {

    private bool isGrounded = false;
    private Vector2 movement, jumpMovement;
    public float jumpHeight = 1000f;
    public float speed = 8.0f;

    void FixedUpdate()
    {
        float inputX = Input.GetAxis("Horizontal");

        //isGrounded = Physics2D.Raycast(transform.position, -Vector2.up, .4f);
        isGrounded = (rigidbody2D.velocity.y == 0);

        transform.position += transform.right * inputX * speed * Time.deltaTime;

// Changed GetButtonMove() to GetButton; moved from Update to FixedUpdate()
		if(Input.GetButton("Jump") && isGrounded){
			Jump();
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
}
