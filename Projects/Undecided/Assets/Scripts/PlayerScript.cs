using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {

    private bool isGrounded = false;
    private Vector2 movement, jumpMovement;
    public float jumpHeight = 1000f;
    public float speed = 8.0f;
	
	// Update is called once per frame
	void Update () {
        if(Input.GetButtonDown("Jump") && isGrounded){
            Jump();
        }
	}

    void FixedUpdate()
    {
        float inputX = Input.GetAxis("Horizontal");

        //isGrounded = Physics2D.Raycast(transform.position, -Vector2.up, .4f);
        isGrounded = (rigidbody2D.velocity.y == 0);

        transform.position += transform.right * inputX * speed * Time.deltaTime;
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
