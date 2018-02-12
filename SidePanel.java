package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SidePanel extends JPanel {

	private static final long serialVersionUID = 2181495598854992747L;

	private static final int TILE_SIZE = BoardPanel.TILE_SIZE >> 1;

	private static final int SHADE_WIDTH = BoardPanel.SHADE_WIDTH >> 1;

	private static final int TILE_COUNT = 5;

	private static final int SQUARE_CENTER_X = 130;

	private static final int SQUARE_CENTER_Y = 65;

	private static final int SQUARE_SIZE = (TILE_SIZE * TILE_COUNT + 20 >> 1);

	private static final int SMALL_INSET = 20;

	private static final int LARGE_INSET = 40;

	private static final int STATS_INSET = 175;

	private static final int CONTROLS_INSET = 300;

	private static final int TEXT_STRIDE = 25;

	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);

	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);

	private static final Color DRAW_COLOR = new Color(128, 192, 128);

	private Tetris tetris;

	public SidePanel(Tetris tetris) {
		this.tetris = tetris;
		
		setPreferredSize(new Dimension(200, BoardPanel.PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
		g.setColor(DRAW_COLOR);
	
		int offset;
	
		g.setFont(LARGE_FONT);
		g.drawString("Статус:", SMALL_INSET, offset = STATS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("Рівень: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Очки: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
		
	
		g.setFont(LARGE_FONT);
		g.drawString("Управління:", SMALL_INSET, offset = CONTROLS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("Left - Вліво", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Right - Вправо", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Up - Повернути", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Down - Кинути", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("P - Пауза", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("M - Звук", LARGE_INSET, offset += TEXT_STRIDE);

		g.setFont(LARGE_FONT);
		g.drawString("Наступна:", SMALL_INSET, 70);
		g.drawRect(SQUARE_CENTER_X - SQUARE_SIZE, SQUARE_CENTER_Y - SQUARE_SIZE, SQUARE_SIZE * 2, SQUARE_SIZE * 2);

		TileType type = tetris.getNextPieceType();
		if(!tetris.isGameOver() && type != null) {
		
			int cols = type.getCols();
			int rows = type.getRows();
			int dimension = type.getDimension();
	
			int startX = (SQUARE_CENTER_X - (cols * TILE_SIZE / 2));
			int startY = (SQUARE_CENTER_Y - (rows * TILE_SIZE / 2));
		
			int top = type.getTopInset(0);
			int left = type.getLeftInset(0);
		
			for(int row = 0; row < dimension; row++) {
				for(int col = 0; col < dimension; col++) {
					if(type.isTile(col, row, 0)) {
						drawTile(type, startX + ((col - left) * TILE_SIZE), startY + ((row - top) * TILE_SIZE), g);
					}
				}
			}
		}
	}
	private void drawTile(TileType type, int x, int y, Graphics g) {
	
		g.setColor(type.getBaseColor());
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
	
		g.setColor(type.getDarkColor());
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
	
		g.setColor(type.getLightColor());
		for(int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}
	
}
