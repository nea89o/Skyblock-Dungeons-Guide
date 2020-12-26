package kr.syeyoung.dungeonsguide.config;

import kr.syeyoung.dungeonsguide.features.AbstractFeature;
import kr.syeyoung.dungeonsguide.features.GuiFeature;
import kr.syeyoung.dungeonsguide.roomedit.MPanel;
import kr.syeyoung.dungeonsguide.roomedit.elements.MButton;
import kr.syeyoung.dungeonsguide.roomedit.elements.MLabel;
import kr.syeyoung.dungeonsguide.roomedit.elements.MStringSelectionButton;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.Gui;
import scala.actors.threadpool.Arrays;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MFeature extends MPanel {
    private MLabel label;

    @Getter
    private AbstractFeature feature;

    private List<MPanel> addons =  new ArrayList<MPanel>();

    @Getter @Setter
    private Color hover;

    public MFeature(AbstractFeature abstractFeature) {
        this.feature = abstractFeature;

        this.add(this.label = new MLabel());
        this.label.setText(abstractFeature.getName());

        if (abstractFeature instanceof GuiFeature) {
            MButton button = new MButton();
            button.setText("GUI");
            addons.add(button);
            add(button);
        }
        if (!abstractFeature.getParameters().isEmpty()) {
            MButton button = new MButton();
            button.setText("Edit");
            addons.add(button);
            add(button);
        }
        {
            final MStringSelectionButton mStringSelectionButton = new MStringSelectionButton(new ArrayList<String>(Arrays.asList(new String[] {"on", "off"})), abstractFeature.isEnabled() ? "on" : "off");
            mStringSelectionButton.setOnUpdate(new Runnable() {
                @Override
                public void run() {
                    String selected = mStringSelectionButton.getSelected();
                    feature.setEnabled("on".equals(selected));
                }
            });
            addons.add(mStringSelectionButton);
            add(mStringSelectionButton);
        }
    }

    @Override
    public void render(int absMousex, int absMousey, int relMousex0, int relMousey0, float partialTicks, Rectangle scissor) {
        if (hover != null && new Rectangle(new Point(0,0),bounds.getSize()).contains(relMousex0, relMousey0)) {
            Gui.drawRect(0,0,bounds.width, bounds.height, hover.getRGB());
        }
    }

    @Override
    public void resize(int parentWidth, int parentHeight) {
        this.setSize(new Dimension(parentWidth, bounds.height));
    }

    @Override
    public void onBoundsUpdate() {
        int x = bounds.width - 70;
        for (MPanel panel : addons) {
            panel.setBounds(new Rectangle(x, 0, 70, bounds.height));
            x -= 70;
        }
        label.setBounds(new Rectangle(0,0,x, bounds.height));
    }
}
