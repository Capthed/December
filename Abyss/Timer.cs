using System;

namespace Capthed.Abyss {
	
	public static class Timer {

		private static long delta;
		// Returns the time from the last tick to this one.
		public static long Delta { get { return delta; }}
		public static readonly int SECOND = 1000;
		private static readonly DateTime Jan1st1970 = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);

		// Returns the current time in ms.
		public static long GetTime() {
			return (long)(DateTime.UtcNow - Jan1st1970).TotalMilliseconds;;
		}

		public static void SetDelta(long ms) {
			delta = ms;
		}
	}
}

