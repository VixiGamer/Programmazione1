package snippet;

public class Snippet {
	
	JTextField genereField = new JTextField(25);
	genereField.setBackground(Color.WHITE);
	JComboBox<Artista.FormatoMusica> formatoBox = new JComboBox<>(Artista.FormatoMusica.values());
	formatoBox.setBackground(Color.WHITE);
	JComboBox<Artista.TipoMusica> tipoMusicaBox = new JComboBox<>(Artista.TipoMusica.values());
	tipoMusicaBox.setBackground(Color.WHITE);
	
	// Nuova checkbox Deluxe Edition
	JCheckBox deluxeEditionCheckBox = new JCheckBox("Deluxe Edition");
	deluxeEditionCheckBox.setBackground(new Color(240, 240, 240));
	JCheckBox edizioneLimitataCheckBox = new JCheckBox("Edizione Limitata");
	edizioneLimitataCheckBox.setBackground(new Color(240, 240, 240));
	
	// Pannello per le due checkbox affiancate
	JPanel editionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	editionPanel.setBackground(new Color(240, 240, 240));
	editionPanel.add(deluxeEditionCheckBox);
	editionPanel.add(Box.createHorizontalStrut(16));
	editionPanel.add(edizioneLimitataCheckBox);
	
	JTextField esclusiveField = new JTextField(25);
	esclusiveField.setBackground(Color.WHITE);
	
}

