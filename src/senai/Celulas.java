package senai;

public class Celulas {

	// Essa classe representa uma eelula do jogo

	public String conteudo;
	public boolean vazio;

	public Celulas() {

		this.conteudo = " ";
		this.vazio = true;
	}

	public String output() {

		return this.conteudo;
	}

	public void marcador() {

		if (TicTacToe.count % 2 == 0) {
			this.conteudo = "X";
		} else {
			this.conteudo = "O";
		}

		this.vazio = false;
	}
}