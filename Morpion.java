import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morpion extends JFrame {

    private JButton[][] boardButtons;
    private char currentPlayer;
    private boolean gameOver;

    public Morpion() {
        boardButtons = new JButton[3][3];
        currentPlayer = 'X';
        gameOver = false;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = new JButton("");
                boardButtons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                boardButtons[i][j].setFocusPainted(false);
                int finalI = i;
                int finalJ = j;
                boardButtons[i][j].addActionListener(e -> onButtonClick(finalI, finalJ));
                boardPanel.add(boardButtons[i][j]);
            }
        }

        getContentPane().add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        getContentPane().add(resetButton, BorderLayout.SOUTH);
    }

    private void onButtonClick(int row, int col) {
        if (!gameOver && boardButtons[row][col].getText().equals("")) {
            boardButtons[row][col].setText(Character.toString(currentPlayer));
            if (checkWinner(row, col)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                if (currentPlayer == 'O') {
                    makeAIMove();
                }
            }
        }
    }

    private void makeAIMove() {
        // TODO: Implémenter l'algorithme minimax et la fonction d'évaluation heuristique ici
    }

    private boolean checkWinner(int row, int col) {
        // Vérifie les lignes, colonnes et diagonales pour voir s'il y a un gagnant
        return (checkRow(row) || checkColumn(col) || checkDiagonals());
    }

    private boolean checkRow(int row) {
        return (boardButtons[row][0].getText().equals(boardButtons[row][1].getText()) &&
                boardButtons[row][1].getText().equals(boardButtons[row][2].getText()) &&
                !boardButtons[row][0].getText().equals(""));
    }

    private boolean checkColumn(int col) {
        return (boardButtons[0][col].getText().equals(boardButtons[1][col].getText()) &&
                boardButtons[1][col].getText().equals(boardButtons[2][col].getText()) &&
                !boardButtons[0][col].getText().equals(""));
    }

    private boolean checkDiagonals() {
        return ((boardButtons[0][0].getText().equals(boardButtons[1][1].getText()) &&
                boardButtons[1][1].getText().equals(boardButtons[2][2].getText()) &&
                !boardButtons[0][0].getText().equals("")) ||
                (boardButtons[0][2].getText().equals(boardButtons[1][1].getText()) &&
                        boardButtons[1][1].getText().equals(boardButtons[2][0].getText()) &&
                        !boardButtons[0][2].getText().equals("")));
    }

    private boolean isBoardFull() {
        // Vérifie si le plateau est complet (match nul)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        // Réinitialise le jeu
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
        gameOver = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Morpion morpion = new Morpion();
            morpion.setVisible(true);
        });
    }
}
