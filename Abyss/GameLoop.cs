using System;
using System.Threading;
using Capthed.Util;

namespace Capthed.Abyss {
	
	public static class GameLoop {

		private static bool isAlive = false;
		public static bool IsAlive { get { return isAlive; } set { isAlive = value; }}
		private static int fps;
		public static int Fps { get { return fps; } set { fps = value; }}
		public static int CurrFps { get; set; }
		public static int CurrUps { get; set; }

		public static void Loop() {
			Debug.Print ("Main GameLoop started");
			// init context dependent stuff and update, render

			long lastTime = Timer.GetTime ();
			long startTime = Timer.GetTime ();
			double msPerSecond = 1000.0 / fps;
			long elapsedTime = 0;
			int ups = 0;
			int fpsCount = 0;
			long timeCount = 0;

			while (isAlive) {
				startTime = Timer.GetTime ();
				elapsedTime += (startTime - lastTime);

				timeCount += (startTime - lastTime);

				if (elapsedTime >= msPerSecond) {
					Timer.SetDelta (elapsedTime);
					elapsedTime = 0;
					ups++;

					Update ();
				}
				Render ();
				fpsCount++;

				// FPS and UPS meter.
				if (timeCount >= Timer.SECOND) {
					timeCount = 0;
					CurrFps = fpsCount;
					CurrUps = ups;
					ups = 0;
					fpsCount = 0;
					Debug.Print ("FPS: " + CurrFps);
					Debug.Print ("UPS: " + CurrUps);
				}

				lastTime = startTime;
			}
				
			Debug.KeepOpen ();
		}

		private static void Init() {}

		private static void Update() {}

		private static void Render() {}

		private static void InitSubsystems() {}
	}
}