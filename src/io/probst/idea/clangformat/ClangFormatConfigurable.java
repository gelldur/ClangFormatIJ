package io.probst.idea.clangformat;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * ClangFormatConfigurable provides UI to configure {@link Settings} for the clang-format plugin.
 */
public class ClangFormatConfigurable implements Configurable {
  private Settings settings = Settings.get();

  private JTextField clangFormatBinary;
  private JTextField path;
  private JPanel configurationForm;

  @Nls
  @Override
  public String getDisplayName() {
    return "clang-format";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    reset();
    return configurationForm;
  }

  @Override
  public boolean isModified() {
    return !Objects.equals(settings.clangFormatBinary, clangFormatBinary.getText())
        || !Objects.equals(settings.path, path.getText());
  }

  @Override
  public void apply() throws ConfigurationException {
    settings = Settings.update(clangFormatBinary.getText(), path.getText());
  }

  @Override
  public void reset() {
    clangFormatBinary.setText(settings.clangFormatBinary);
    path.setText(settings.path);
  }

  @Override
  public void disposeUIResources() { /* empty */ }
}
