package senai;

public class Jogador {

	// Um jogador pode ser um Humano ou o computador

	private String tipo;
	private int indice;
	private int coluna;
	private int linha;
	private boolean jogada;

	// A string do construtor seta o tipo do jogo
	public Jogador(String tipo) {
		this.tipo = tipo;
	}

	public void rodada() {

		this.jogada = true;

		if (tipo == "COMPUTADOR") {
			System.out.print("\tVez do computador ...");
			delay(1000, TicTacToe.jogo.getTamanhoTabela());

			while (jogada) {
				indice = (int) Math
						.round((TicTacToe.jogo.getTamanhoTabela() * TicTacToe.jogo.getTamanhoTabela() - 1) * Math.random());
				marcarCelula(indice, TicTacToe.jogo);
			}

		} else {
			// se for jogador(es) humano(s) ...

			TicTacToe.escolhaUsuario = TicTacToe.getInput("\tAguardando jogada: Ex: 1A, 1B, 1C, 2A, etc.: ");

			while (jogada) {

				// validate user input
				if (entradaValida(TicTacToe.escolhaUsuario)) {
					if (TicTacToe.escolhaUsuario.length() == 2) {
						coluna = Integer.parseInt(TicTacToe.escolhaUsuario.substring(0, 1));
						linha = converterLetrasParaNumeros(TicTacToe.escolhaUsuario.substring(1, 2));

					} else {

						coluna = Integer.parseInt(TicTacToe.escolhaUsuario.substring(0, 2));
						linha = converterLetrasParaNumeros(TicTacToe.escolhaUsuario.substring(2, 3));

					}

					indice = TicTacToe.jogo.getTamanhoTabela() * (linha - 1) + (coluna - 1);

					if (indice > (TicTacToe.jogo.getTamanhoTabela() * TicTacToe.jogo.getTamanhoTabela()) - 1 || indice < 0) {

						TicTacToe.escolhaUsuario = TicTacToe
								.getInput("Essa não é uma celula válido! Escolha outro celula: ");
					} else {

						// if valid input, and cell isn't taken already,
						// place mark in selected cell and end turn
						marcarCelula(indice, TicTacToe.jogo);

						if (jogada) {

							TicTacToe.escolhaUsuario = TicTacToe
									.getInput("Celula ja ocupada! Escolha outra: ");
						}

					}

				} else {

					TicTacToe.escolhaUsuario = TicTacToe
							.getInput("Essa não é uma celula válido! Escolha outro celula: ");
				}
			}
		}
	}

	// código encapsulado para validação de entrada do usuário

	private static boolean entradaValida(String escolhaUsuario) {

		boolean output = false;

		if (escolhaUsuario.length() == 2) {

			output = (escolhaUsuario.substring(0, 1).matches("[0-9]")
					&& escolhaUsuario.substring(1, 2).matches("[a-zA-Z]"));
		} else if (escolhaUsuario.length() == 3) {

			output = (escolhaUsuario.substring(0, 2).matches("[1-2][0-9]")
					&& escolhaUsuario.substring(2, 3).matches("[a-zA-Z]"));

			if (Integer.parseInt(escolhaUsuario.substring(0, 2)) > TicTacToe.jogo.getTamanhoTabela()) {
				output = false;
			}
		}

		return output;
	}

	
	private void marcarCelula(int index, Jogo game) {

		if (TicTacToe.jogo.setCell(index)) {
			jogada = false;
		}
	}


	private static void delay(int amount, int gameSize) {
		try {

			Thread.sleep(amount * 3 / (gameSize * gameSize));

		} catch (InterruptedException ex) {

			Thread.currentThread().interrupt();
		}
	}
	

	// Metodo para converter a entrada da letra para a seleção de linha / coluna em
	// um número utilizável
	private static int converterLetrasParaNumeros(String str) {
		return ("ABCabc".indexOf(str)) % 3 + 1;
	}
}