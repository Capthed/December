using System;
using Capthed.Util;

namespace Capthed.Abyss {
	
	public static class GameLoop {

		private static bool isAlive = false;
		public static bool IsAlive { get { return isAlive; } set { isAlive = value; }}

		public static void Loop() {
			Debug.Print ("Main GameLoop started");
			// init context dependent stuff and update, render
			Debug.KeepOpen ();
		}

		private static void Init() {}

		private static void Update() {}

		private static void Render() {}

		private static void InitSubsystems() {}
	}
}