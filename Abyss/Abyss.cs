using System;
using System.Threading;
using Capthed.Util;

namespace Capthed.Abyss {
	
	public static class Abyss {

		private static readonly string version = "";
		public static string VERSION { get { return version; } }

		private static readonly Thread MAIN_LOOP = new Thread (GameLoop.Loop);

		public static void Main () {	
			Debug.DEBUG = true;
			SetFps (120);

			// init engine non-context stuff

			Start();
		}

		public static void Start() {
			GameLoop.IsAlive = true;
			MAIN_LOOP.Start ();
		}

		public static void Stop(bool kill) {
			GameLoop.IsAlive = false;

			if (kill)
				System.Environment.Exit (0);
			// close thread?
		}

		// Temporary set of fps.
		public static void SetFps(int fps) {
			GameLoop.Fps = fps;
		}
	}
}