package com.sugar.jshining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Shining {
    private final static String CRAZY_WORD_PASCAL = "AllWorkAndNoPlayMakeJackADullBoy";
    private final static String CRAZY_WORD_BLANK = "All work and no play make jack a dull boy";

    private Map<Character, Character> filter;
    private Set<Character> filterSet;

    private JFrame frame;

    public Shining() {
        filter = new HashMap<>();
        filterSet = new HashSet<>();
        initFilter();
    }

    private void initFilter(){
        filterSet.add(' ');
        filterSet.add('\n');
        filterSet.add('/');
        filterSet.add('{');
        filterSet.add('}');
        filterSet.add('=');
        filterSet.add('+');
        filterSet.add(')');
        filterSet.add('(');
        filterSet.add(';');
        filterSet.add('<');
        filterSet.add('>');
        filterSet.add('.');
        filterSet.add('\'');
        filterSet.add('\"');
        filterSet.add(',');
    }

    public void addFilter(Character k, Character v) {
        filter.put(k, v);
    }

    public void addFilter(Character c) {
        filterSet.add(c);
    }

    public void removeFilter(Character k) {
        filter.remove(k);
        filterSet.remove(k);
    }

    public void clearFilter() {
        filter.clear();
        filterSet.clear();
    }

    public String shiningByChar(String codeStr) {
        StringBuilder shinStr = new StringBuilder();
        for (int i = 0, index = 0; i < codeStr.length(); i++) {
            char c = codeStr.charAt(i);
            if (filter.containsKey(c)) {
                shinStr.append(filter.get(c));
            } else if (filterSet.contains(c)) {
                shinStr.append(c);
            } else {
                shinStr.append(CRAZY_WORD_PASCAL.charAt(index));
                index++;
            }
            if (index == CRAZY_WORD_PASCAL.length()) {
                index = 0;
            }
        }
        return shinStr.toString();
    }

    public String shiningByWord(String codeStr) {
        StringBuilder shinStr = new StringBuilder();
        String[] crazyWords = CRAZY_WORD_BLANK.split(" ");
        char last = ' ';
        for (int i = 0, index = 0; i < codeStr.length(); i++) {
            char c = codeStr.charAt(i);
            if (filter.containsKey(c) || filterSet.contains(c)) {
                if (!(filter.containsKey(last) || filterSet.contains(last))) {
                    shinStr.append(crazyWords[index]);
                    index++;
                }
                if (filter.containsKey(c)) {
                    shinStr.append(filter.get(c));
                } else {
                    shinStr.append(c);
                }
            }
            if (index == crazyWords.length) {
                index = 0;
            }
            last = c;
        }
        return shinStr.toString();
    }

    public void start() {
        frame = new JFrame("Java shining");
        frame.setBounds(200, 200, 1600, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        JPanel ioPanel = new JPanel();
        ioPanel.setLayout(new GridLayout(1, 2, 2, 2));
        JPanel formPanel = new JPanel();
        JScrollPane inputPanel = new JScrollPane();
        JTextArea inputText = new JTextArea();
        inputPanel.setViewportView(inputText);
        ioPanel.add(inputPanel);
        JScrollPane outputPanel = new JScrollPane();
        JTextArea outputText = new JTextArea();
        outputPanel.setViewportView(outputText);
        outputText.setEditable(false);
        ioPanel.add(outputPanel);
        JButton codeBtn = new JButton("shine by word");
        codeBtn.addActionListener(e -> {
            String codeStr = inputText.getText();
            outputText.setText(shiningByWord(codeStr));
        });
        JButton chBtn = new JButton("shine by char");
        chBtn.addActionListener(e -> {
            outputText.setText(shiningByChar(inputText.getText()));
        });
        formPanel.add(codeBtn);
        formPanel.add(chBtn);
        frame.add(formPanel, BorderLayout.SOUTH);
        frame.add(ioPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Shining shining = new Shining();
        shining.start();
    }
}
