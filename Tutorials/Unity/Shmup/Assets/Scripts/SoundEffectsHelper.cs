using UnityEngine;
using System.Collections;

public class SoundEffectsHelper : MonoBehaviour 
{
	// Singleton
	public static SoundEffectsHelper Instance;

	public AudioClip explosionSound;
	public AudioClip playerShotSound;
	public AudioClip enemyShotSound;

	void Awake() 
	{
		// Register the singleton
		if (Instance != null)
		{
			Debug.LogError("Multiple instances of SoundEffectsHelper!");
		}

		Instance = this;
	}

	public void MakeExplosionSound() 
	{
		MakeSound(explosionSound);
	}

	public void MakePlayerShotSound()
	{
		MakeSound(playerShotSound);
	}

	public void MakeEnemyShotSound()
	{
		MakeSound(enemyShotSound);
	}

	// Play a given sound
	private void MakeSound(AudioClip originalClip)
	{
		// As it is not 3D audio clip, position doesn't matter.
		AudioSource.PlayClipAtPoint(originalClip, transform.position);
	}
}
