import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToe() {
        setTitle("TicTacToe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        currentPlayer = 'X';
        setVisible(true);
    }

    private void initializeButtons() {
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Wird schon benutzt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        clickedButton.setText(Character.toString(currentPlayer));
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Spieler " + currentPlayer + " gewinnt!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            return;
        }
        if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "unentschieden", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            return;
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(currentPlayer + "") && board[i][1].equals(currentPlayer + "") && board[i][2].equals(currentPlayer + "")) {
                return true;
            }
            if (board[0][i].equals(currentPlayer + "") && board[1][i].equals(currentPlayer + "") && board[2][i].equals(currentPlayer + "")) {
                return true;
            }
        }
        if (board[0][0].equals(currentPlayer + "") && board[1][1].equals(currentPlayer + "") && board[2][2].equals(currentPlayer + "")) {
            return true;
        }
        return board[0][2].equals(currentPlayer + "") && board[1][1].equals(currentPlayer + "") && board[2][0].equals(currentPlayer + "");
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
