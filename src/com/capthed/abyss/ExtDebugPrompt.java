package com.capthed.abyss;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

public class ExtDebugPrompt {

	private static final String PROMPT = ">:";
	
	private JFrame frame;
	private JPanel panel;
	private JTextArea console;
	private Filter filter;

	public ExtDebugPrompt() {
		init();
	}

	private void init() {
		frame = new JFrame("Abyss " + Abyss.getVersion() + " - Debug Prompt");
		panel = new JPanel(new FlowLayout());

		filter = new Filter();
		console = new JTextArea(30, 50);
		
		
		console.setText(PROMPT);
		console.setFont(new Font("consolas", Font.PLAIN, 12));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.GREEN);
		
		console.setCaretPosition(PROMPT.length());

		/*JScrollPane scroll = new JScrollPane (console);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
		
		((AbstractDocument) console.getDocument()).setDocumentFilter(filter);
		
		panel.add(console);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class Filter extends DocumentFilter {
		
		@Override
		public void insertString(DocumentFilter.FilterBypass fb, int offset,
				String string, AttributeSet attr) throws BadLocationException {
			
			if (string == null) {
				return;
			} else {
				replace(fb, offset, 0, string, attr);
			}
		}

		@Override
		public void remove(DocumentFilter.FilterBypass fb, int offset,
				int length) throws BadLocationException {
			
			replace(fb, offset, length, "", null);
		}

		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset,
				int length, String text, AttributeSet attrs)
				throws BadLocationException {
			
			String output = "isus";
			
			Document doc = fb.getDocument();
			Element root = doc.getDefaultRootElement();
			int count = root.getElementCount();
			int index = root.getElementIndex(offset);
			Element cur = root.getElement(index);
			int promptPosition = cur.getStartOffset() + PROMPT.length();
			
			if (index == count - 1 && offset - promptPosition >= 0) {
				if (text.equals("\n")) {
					String cmd = doc.getText(promptPosition, offset
							- promptPosition);
					if (cmd.isEmpty()) {
						text = "\n" + PROMPT;
					} else {
						text = "\n" + output + "\n\n" + PROMPT;
					}
				}
				fb.replace(offset, length, text, attrs);
			}
		}
	}
}
