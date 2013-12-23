using UnityEngine;

// Creating instance of particles from code with no effort
public class SpecialEffectsHelper : MonoBehaviour 
{
	// Singleton
	public static SpecialEffectsHelper Instance;

	public ParticleSystem smokeEffect;
	public ParticleSystem fireEffect;
	
	void Awake() 
	{
		// Register the singleton
		if (Instance != null)
		{
			Debug.LogError("Multiple instances of SpecialEffectsHelper!");
		}

		Instance = this;
	}

	// Create an explosion at the given location
	public void Explosion(Vector3 position) 
	{
		// Smoke on the water
		instantiate(smokeEffect, position);

		// Fire in the sky
		instantiate(fireEffect, position);
	}

	// Instantiate a Particle system from prefab
	private ParticleSystem instantiate(ParticleSystem prefab, Vector3 position)
	{
		ParticleSystem newParticleSystem = Instantiate(
			prefab,
			position,
			Quaternion.identity
		) as ParticleSystem;

		// Make sure it will be destroyed
		Destroy(
			newParticleSystem.gameObject,
			newParticleSystem.startLifetime
		);

		return newParticleSystem;
	}
}
