/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.dmn.xlsx;

import org.camunda.bpm.dmn.xlsx.api.Spreadsheet;
import org.camunda.bpm.dmn.xlsx.api.SpreadsheetCell;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.STCellType;

import java.util.regex.Pattern;

/**
 * @author Thorben Lindhauer
 *
 */
public class FeelSimpleUnaryTestConverter implements CellContentHandler {

  public static final Pattern REGEX = Pattern.compile("^((?:<|>)(?!.+(<|>))).+");

  public boolean canConvert(SpreadsheetCell cell, Spreadsheet context) {
    Cell rawCell = cell.getRaw();

    if (!STCellType.S.equals(rawCell.getT())) {
      return false;
    }

    String rawContent = context.resolveCellContent(cell);
    return REGEX.matcher(rawContent).matches();
  }

  public String convert(SpreadsheetCell cell, Spreadsheet context) {
    return context.resolveCellContent(cell);
  }
}
