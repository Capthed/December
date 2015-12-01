using System;
using System.Collections.Generic;

namespace Capthed.Util {

	public static class Debug {

		// If the debug is false no code from this class will be executed when called.
		private static bool debug = false;
		public static bool DEBUG { get { return debug; } set { debug = value; } }

		// Prints the txt followed by t and a new line depending on the bool.
		public static void Print<T>(T t, string txt = "", bool newLine = true) {
			if (!DEBUG)
				return;

			Console.Write (t.ToString() + (newLine ? "\n" : ""));
		}

		// Prints the txt followed by every element in t with a with/without new line after
		// every element depending on the bool.
		public static void Print<T>(List<T> list, string txt = "", bool newLine = false) {
			if (!DEBUG) return;

			string temp = "";
			Console.Write (txt);
			foreach (T t in list)
				temp += t.ToString() + (newLine ? "\n" : ", ");

			if (!newLine)
				temp = temp.Remove (temp.Length - 2);

			Console.WriteLine (temp);
		}

		// Prints the txt followed by every element in t with a with/without new line after
		// every element depending on the bool.
		public static void Print<T>(T[] array, string txt = "", bool newLine = false) {
			if (!DEBUG) return;

			string temp = "";
			Console.Write (txt);
			foreach (T t in array)
				temp += t.ToString() + (newLine ? "\n" : ", ");

			if (!newLine)
				temp = temp.Remove (temp.Length - 2);

			Console.WriteLine (temp);
		}

		// Awaits user input to keep the command prompt open at program end.
		public static void KeepOpen() {
			if (!DEBUG)
				return;

			Console.ReadKey ();
		}
	}
}