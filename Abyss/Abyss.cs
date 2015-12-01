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

			// init engine non-context stuff

			Start();
		}

		private static void Start() {
			GameLoop.IsAlive = true;
			MAIN_LOOP.Start ();
		}

		private static void Stop() {
			GameLoop.IsAlive = false;
			// close thread?
		}
	}
}